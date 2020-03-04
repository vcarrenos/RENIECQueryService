package pe.gob.midis.sisfoh.action;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.midis.sisfoh.bean.AdultDNIResponse;
import pe.gob.midis.sisfoh.mock.RENIECQueryMock;
import pe.gob.midis.sisfoh.model.AuditLog;
import pe.gob.midis.sisfoh.service.RENIECQueryService;
import pe.gob.midis.sisfoh.type.EventType;
import pe.gob.midis.sisfoh.type.PropertiesType;
import pe.gob.midis.sisfoh.utils.GeneralEntitiesHelper;
import pe.gob.midis.sisfoh.utils.PropertiesHelper;
import pe.gob.midis.sisfoh.utils.StringUtil;

import com.opensymphony.xwork2.ActionSupport;

public class SearchByBatchAction extends ActionSupport 
	implements SessionAware, ServletRequestAware, ServletResponseAware {

	private static final Logger LOG = LoggerFactory.getLogger(SearchByBatchAction.class);
	private static final long serialVersionUID = -7143272536998100967L;

	private File upload; 
	private String uploadContentType; 
	private String uploadFileName; 
	private String fileCaption;
 
    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;

	private String jsonData;
	private String jsonErrorMessage;
	private boolean downloadFinish;
	
	String subqueryType;
	
	private Map<String, Object> session;
	
	public SearchByBatchAction() throws JSONException {

		session = new HashMap<String, Object>();
	}
	
	public String verifyDownloadFinish() {
		
		String filePath = servletRequest.getServletContext().getRealPath("/") + "upload/";
		File file = new File(filePath, servletRequest.getSession().getId() + ".downloadFileName");
		
		if ( (downloadFinish = file.exists()) )
			file.delete();
		
		return ActionSupport.SUCCESS;
	}

	public String execute() {

		try {
			
			if (!StringUtil.isNullOrEmpty(this.uploadFileName)) {
				
				// Load the DNI's file
				String filePath = servletRequest.getServletContext().getRealPath("/") + "upload/";
				File fileToCreate = new File(filePath, this.uploadFileName);
				
				LOG.info(String.format(getText("querybybatch.action.event"), filePath, this.uploadFileName));

				FileUtils.copyFile(this.upload, fileToCreate);
				
				List<String> responseList = processFile(filePath, this.uploadFileName);
				String[]fileNameParts = this.uploadFileName.split("\\.(?=[^\\.]+$)");
				String downloadFileName = fileNameParts[0] + "-" + System.currentTimeMillis() + '.' + fileNameParts[1];
		        String headerValue = String.format("attachment; filename=\"%s\"", downloadFileName );
		        
		        servletResponse.setHeader("Content-Disposition", headerValue);
				servletResponse.setContentType("application/zip");
		        
				ServletOutputStream outStream = servletResponse.getOutputStream();
				
//				listToZIPFile(outStream, responseList, this.uploadFileName);
				
				for (String string : responseList) {
					
					outStream.write(string.getBytes("UTF8"));
					outStream.write("\r\n".getBytes("UTF8"));
					
				}

				File file = new File(filePath, servletRequest.getSession().getId() + ".downloadFileName");
				
				FileUtils.writeByteArrayToFile(file, new byte[]{0x56, 0x43, 0x53});
				
				outStream.flush();
				outStream.close();
				
				servletResponse.flushBuffer();
				servletResponse.reset();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();

		}
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	List<String> processFile(String filePath, String fileName) throws IOException,
			JSONException {
		
		HashMap<String,Object> userMap = (HashMap<String,Object>)JSONUtil.deserialize((String)session.get("user"));
		String user = (String)userMap.get("user");

		List<String> dniList = readFile(filePath + fileName);
		List<String> responseList = new ArrayList<String>();
		
		int count = 0;
		
		for (String numberId : dniList) {

			jsonErrorMessage = null;

			PropertiesType props = PropertiesHelper.getProperties();
			AdultDNIResponse adultDNI = null;
			
			numberId = numberId.trim();
			
			if (numberId.length() == 0)
				continue;
			
			if (numberId.length() > 8) {
			
				LOG.info(String.format("The DNI '%s' exceeds the maximum length", numberId));
				continue;
			}

			try {
				
				if (!props.isApplMock()) {
					RENIECQueryService service = new RENIECQueryService();

					adultDNI = service.queryByAdultId(user, numberId, getSubqueryType());
				} else {
					RENIECQueryMock mock = new RENIECQueryMock();

					adultDNI = mock.queryByAdultId(user, numberId, getSubqueryType());
				}
				
				if ( ((++count) % props.getAppQuantityQueryBatch()) == 0) {
					
					LOG.info(String.format("Waiting '%d' seconds to the next block query", props.getAppDelayQueryBatch() / 1000));
					Thread.sleep(props.getAppDelayQueryBatch());
				}

				responseList.add(adultDNI.toStringLine());
				
				GeneralEntitiesHelper.saveAuditLog(new AuditLog(
						EventType.__QUERYBYBATCH, user, String.format(
								getText("querybyid.action.event"), numberId)));

			} catch (Exception e) {
				
				String message[] = e.getMessage().split("\\[");
				
				if ( message.length >= 3 ) {
					
					String code = message[1].substring(0, message[1].length()-1);
					String text = message[2].substring(0, message[2].length()-1);
	
					responseList.add(String.format("[%s][%8s][%s]", code, numberId, text));
				}
				else
					responseList.add(e.getMessage());
				
				e.printStackTrace();
			}

		}
		
		return responseList;
		
	}
	
	List<String> readFile(String fileName) {

		BufferedReader br;
		String line;
		List<String> lines = null;
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			lines = new ArrayList<String>();

			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return lines;
	}
	
	public void listToZIPFile(OutputStream zipFile,
			List<String> lines, String fileName) throws IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try (ZipOutputStream zos = new ZipOutputStream(baos)) {

			/*
			 * File is not on the disk, 'fileName' indicates only the file name to
			 * be put into the zip
			 */
			ZipEntry entry = new ZipEntry(fileName);

			zos.putNextEntry(entry);
			
			for (String response : lines) {
				zos.write(response.getBytes());
			}
			zos.closeEntry();
			
			baos.writeTo(zipFile);
			baos.close();
			
			zipFile.flush();
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}		

	}
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFileCaption() {
		return fileCaption;
	}

	public void setFileCaption(String fileCaption) {
		this.fileCaption = fileCaption;
	}
	
	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getJsonErrorMessage() {
		return jsonErrorMessage;
	}

	public void setJsonErrorMessage(String jsonErrorMessage) {
		this.jsonErrorMessage = jsonErrorMessage;
	}
	
	public boolean getDownloadFinish() {
		return downloadFinish;
	}

	public void setDownloadFinish(boolean downloadFinish) {
		this.downloadFinish = downloadFinish;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {

		this.session = session;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {

		servletRequest = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		servletResponse = response;
	}

	public String getSubqueryType() {
		return subqueryType;
	}

	public void setSubqueryType(String subqueryType) {
		this.subqueryType = subqueryType;
	}

}
