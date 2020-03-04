-- Drop Tables
-- ----------------------------------------------------------------------
DROP TABLE `grl_address_prefix`;
DROP TABLE `grl_block_or_chalet_prefix`;
DROP TABLE `grl_dpto_floor_internal_prefix`;
DROP TABLE `grl_urb_condom_resid_prefix`;
DROP TABLE `grl_restriction_prefix`;
DROP TABLE `grl_instruction_level_prefix`;
DROP TABLE `grl_marital_status_prefix`;
DROP TABLE `grl_sex_prefix`;
DROP TABLE `grl_parameter`;
DROP TABLE `grl_user`;
DROP TABLE `grl_audit_log`;


-- TABLE grl_address_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_address_prefix` (
	`code`			TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_address_prefix` VALUES ('01', 'AV.'   );
INSERT INTO `grl_address_prefix` VALUES ('02', 'JR.'   );
INSERT INTO `grl_address_prefix` VALUES ('03', 'CALLE' );
INSERT INTO `grl_address_prefix` VALUES ('04', 'PARQUE');
INSERT INTO `grl_address_prefix` VALUES ('05', 'PSJ.'  );
INSERT INTO `grl_address_prefix` VALUES ('06', 'OVALO' );


-- TABLE grl_block_or_chalet_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_block_or_chalet_prefix` (
	`code`			TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_block_or_chalet_prefix` VALUES ('01', 'BLOCK' );
INSERT INTO `grl_block_or_chalet_prefix` VALUES ('02', 'CHALET');	


-- TABLE grl_dpto_floor_internal_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_dpto_floor_internal_prefix` (
	`code`			TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_dpto_floor_internal_prefix` VALUES ('01', 'DPTO.');
INSERT INTO `grl_dpto_floor_internal_prefix` VALUES ('02', 'EDIF.');
INSERT INTO `grl_dpto_floor_internal_prefix` VALUES ('03', 'INT.' );
INSERT INTO `grl_dpto_floor_internal_prefix` VALUES ('04', 'PISO' );


-- TABLE grl_urb_condom_resid_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_urb_condom_resid_prefix` (
	`code`			TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('01', 'URB'              );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('02', 'URB.IND.'         );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('03', 'URB.POPULAR'      );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('04', 'URB.RES.'         );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('05', 'COND.'            );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('06', 'RES.'             );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('07', 'ASENT.H.'         );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('08', 'ASOC.'            );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('09', 'BARRIO'           );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('10', 'COOP.'            );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('11', 'U.VECINAL'        );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('12', 'AGR.'             );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('13', 'AGRP.'            );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('14', 'ALAM.'            );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('15', 'ALMTE.'           );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('16', 'AMPLC.'           );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('17', 'ANEXO'            );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('18', 'ARQ.'             );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('19', 'BALN.'            );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('20', 'CAMP.MINERO'      );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('21', 'CARRETERA'        );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('22', 'CASERIO'          );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('23', 'CIUDAD'           );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('24', 'CMDTE.'           );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('25', 'COMITE'           );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('26', 'COMUNID.CAMPESINA');
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('27', 'CONJ.HAB.'        );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('28', 'CONJ.RES.'        );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('29', 'CTRLMTE.'         );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('30', 'CRNL.'            );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('31', 'CUADRA'           );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('32', 'FUNDO'            );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('33', 'GALERIA'          );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('34', 'GRAL.'            );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('35', 'GRUPO'            );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('36', 'HACIENDA'         );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('37', 'ISLA'             );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('38', 'MALECON'          );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('39', 'MRCAL.'           );
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('40', 'PASEO'            );           
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('41', 'PLAZA'            ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('42', 'PLAZUELA'         ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('43', '1ER.'             ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('44', 'PROLONG.'         ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('45', 'PUEBLO'           ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('46', 'PUENTE'           ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('47', 'SECTOR'           ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('48', '2DO.'             ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('49', 'TNTE.'            ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('50', 'U.AGROP.'         ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('51', 'VEREDA'           ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('52', 'VILLA'            ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('53', '3ER.'             ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('54', 'ZONA'             ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('55', 'KM.'              ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('56', 'C.POBLADO'        ); 
INSERT INTO `grl_urb_condom_resid_prefix` VALUES ('57', 'COMUNID.NATIVA'   );


-- TABLE grl_restriction_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_restriction_prefix` (
	`code`			TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_restriction_prefix` VALUES (''  , 'NINGUNA'                             );
INSERT INTO `grl_restriction_prefix` VALUES ('A' , 'FALLECIMIENTO'                       );
INSERT INTO `grl_restriction_prefix` VALUES ('04', 'FALLECIMIENTO MENOR'                 );
INSERT INTO `grl_restriction_prefix` VALUES ('R' , 'RENUNCIA A LA NACIONALIDAD PERUANA'  );
INSERT INTO `grl_restriction_prefix` VALUES ('11', 'PERDIDA DE NACIONALIDAD'             );
INSERT INTO `grl_restriction_prefix` VALUES ('_' , 'LEY DEL SERVICIO MILITAR'            );
INSERT INTO `grl_restriction_prefix` VALUES ('«' , 'DECLARACION JUDICIAL DE INTERDICCION');
INSERT INTO `grl_restriction_prefix` VALUES ('3' , 'DECLARADO JUDICIALMENTE AUSENTE'     );
INSERT INTO `grl_restriction_prefix` VALUES (')' , 'DNI S/VIG.EXT.DEL DOM Y ESTCIV.'     );
INSERT INTO `grl_restriction_prefix` VALUES ('[' , 'DOMICILIO FISCALIZADO POR EL JNE'    );
INSERT INTO `grl_restriction_prefix` VALUES (']' , 'DOMICILIO IMPUGANDO POR EL CIUDADANO');
INSERT INTO `grl_restriction_prefix` VALUES ('{' , 'DNI S/VIG.EN EL EXT.DEL DOMIC.'      );
INSERT INTO `grl_restriction_prefix` VALUES ('}' , 'DNI S/VIG.EN EL EXT.DEL ESTCIV.'     );
INSERT INTO `grl_restriction_prefix` VALUES ('¡' , 'ESTADO CIVIL NO ACTUALIZADO'         );
INSERT INTO `grl_restriction_prefix` VALUES ('¶' , 'DOMICILIO NO ACTUALIZADO'            );
INSERT INTO `grl_restriction_prefix` VALUES ('Ø' , 'ESTADO CIVIL Y DOMICILIO IMPUGNADOS' );
INSERT INTO `grl_restriction_prefix` VALUES ('~' , 'MEDIDA CAUTELAR'                     );


-- TABLE grl_instruction_level_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_instruction_level_prefix` (
	`code`			TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_instruction_level_prefix` VALUES (''  , 'NO DETERMINADO'          );
INSERT INTO `grl_instruction_level_prefix` VALUES ('00', 'INICIAL'                 );
INSERT INTO `grl_instruction_level_prefix` VALUES ('06', 'EDUCACION ESPECIAL'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('04', 'ILETRADO/SIN INSTRUCCION');
INSERT INTO `grl_instruction_level_prefix` VALUES ('10', 'PRIMARIA COMPLETA'       );
INSERT INTO `grl_instruction_level_prefix` VALUES ('11', 'PRIMARIA-1ER GRADO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('12', 'PRIMARIA-2DO GRADO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('13', 'PRIMARIA-3ER GRADO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('14', 'PRIMARIA-4TO GRADO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('15', 'PRIMARIA-5TO GRADO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('16', 'PRIMARIA-6TO GRADO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('20', 'SECUNDARIA COMPLETA'     );
INSERT INTO `grl_instruction_level_prefix` VALUES ('21', 'SECUNDARIA-1ER AÑO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('22', 'SECUNDARIA-2DO AÑO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('23', 'SECUNDARIA-3ER AÑO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('24', 'SECUNDARIA-4TO AÑO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('25', 'SECUNDARIA-5TO AÑO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('26', 'SECUNDARIA-6TO AÑO'      );
INSERT INTO `grl_instruction_level_prefix` VALUES ('30', 'SUPERIOR COMPLETA'       );
INSERT INTO `grl_instruction_level_prefix` VALUES ('31', 'SUPERIOR-1ER AÑO'        );
INSERT INTO `grl_instruction_level_prefix` VALUES ('32', 'SUPERIOR-2DO AÑO'        );
INSERT INTO `grl_instruction_level_prefix` VALUES ('33', 'SUPERIOR-3ER AÑO'        );
INSERT INTO `grl_instruction_level_prefix` VALUES ('34', 'SUPERIOR-4TO AÑO'        );
INSERT INTO `grl_instruction_level_prefix` VALUES ('35', 'SUPERIOR-5TO AÑO'        );
INSERT INTO `grl_instruction_level_prefix` VALUES ('36', 'SUPERIOR-6TO AÑO'        );
INSERT INTO `grl_instruction_level_prefix` VALUES ('37', 'SUPERIOR-7MO AÑO'        );
INSERT INTO `grl_instruction_level_prefix` VALUES ('38', 'SUPERIOR-8VO AÑO'        );
INSERT INTO `grl_instruction_level_prefix` VALUES ('50', 'TECNICA COMPLETA'        );
INSERT INTO `grl_instruction_level_prefix` VALUES ('51', 'TECNICA-1ER AÑO'         );
INSERT INTO `grl_instruction_level_prefix` VALUES ('52', 'TECNICA-2DO AÑO'         );
INSERT INTO `grl_instruction_level_prefix` VALUES ('53', 'TECNICA-3ER AÑO'         );
INSERT INTO `grl_instruction_level_prefix` VALUES ('54', 'TECNICA-4TO AÑO'         );
INSERT INTO `grl_instruction_level_prefix` VALUES ('55', 'TECNICA-5TO AÑO'         );


-- TABLE grl_marital_status_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_marital_status_prefix` (
	`code`			TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_marital_status_prefix` VALUES ('01', 'SOLTERO'   );
INSERT INTO `grl_marital_status_prefix` VALUES ('02', 'CASADO'    );
INSERT INTO `grl_marital_status_prefix` VALUES ('03', 'VIUDO'     );
INSERT INTO `grl_marital_status_prefix` VALUES ('04', 'DIVORCIADO');


-- TABLE grl_sex_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_sex_prefix` (
	`code`			TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_sex_prefix` VALUES ('01', 'MASCULINO');
INSERT INTO `grl_sex_prefix` VALUES ('02', 'FEMENINO' );


-- TABLE grl_parameter
-- ----------------------------------------------------------------------
CREATE TABLE `grl_parameter` (
	`code`			TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	`value`			TEXT NOT NULL,
	`enabled`		NUMERIC NOT NULL DEFAULT 1,
	`visible`		INTEGER NOT NULL DEFAULT 1,
	`removable`		INTEGER DEFAULT 1,
	PRIMARY KEY(code)
);
INSERT INTO `grl_parameter` VALUES ('APP_COMPANY_KEY'               , 'LLAVE PARA CIFRADO DE CONTRASEÑA'                             , '842475078609580080600000'      , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_COMPANY_FULL_NAME'         , 'NOMBRE DE LA COMPAÑIA'                                        , 'MINISTERIO DE INCLUSIÓN SOCIAL', 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_COMPANY_ABBREVIATION'      , 'ABREVIATURA DE LA COMPAÑIA'                                   , 'MIDIS'                         , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_DELAY_QUERY_BATCH'         , 'SEGUNDOS DE ESPERA ENTRE CADA CONSULTA DE DNI MASIVO'         , '1'                             , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_QUANTITY_QUERY_BATCH'      , 'CANTIDAD DE REGISTROS EN CADA CONSULTA DE DNI MASIVO'         , '1'                             , 1, 1, 0);

INSERT INTO `grl_parameter` VALUES ('APP_TEST_MQ_HOST'              , 'IP DEL SERVIDOR MQ DE LA RENIEC - DESARROLLO'                 , '172.26.114.40'                 , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_TEST_MQ_CHANNEL_NAME'      , 'NOMBRE DE CANAL MQ - DESARROLLO'                              , 'MIDIS.RENIEC'                  , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_TEST_MQ_PORT_NUMBER'       , 'NÚMERO DE PUERTO EN EL SERVIDOR MQ - DESARROLLO'              , '1414'                          , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_TEST_MQ_MANAGER_NAME'      , 'NOMBRE DEL SERVICIO MQ DE LA RENIEC - DESARROLLO'             , 'RENIECQM'                      , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_TEST_MQ_QUEUE_REQUEST'     , 'NOMBRE DE LA COLA DE REQUERIMIENTO - DESARROLLO'              , 'CENTRADA.MIDIS'                , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_TEST_MQ_QUEUE_RESPONSE'    , 'NOMBRE DE LA COLA DE RESPUESTA - DESARROLLO'                  , 'CRESPUESTA.MIDIS'              , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_TEST_MQ_EXPIRATION_TIME'   , 'TIEMPO DE EXPIRACIÓN (Milisegundos) - DESARROLLO'             , '5000'                          , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_TEST_MQ_INSTITUTION_CODE'  , 'CÓDIGO DE LA INSTITUCIÓN ADMITIDA PARA CONSULTAS - DESARROLLO', 'DE2068'                        , 1, 1, 0);
                                                                                                                                                                       
INSERT INTO `grl_parameter` VALUES ('APP_PROD_MQ_HOST'              , 'IP DEL SERVIDOR MQ DE LA RENIEC - PRODUCCIÓN'                 , '172.26.114.13'                 , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_PROD_MQ_CHANNEL_NAME'      , 'NOMBRE DE CANAL MQ - PRODUCCIÓN'                              , 'MIDIS.RENIECQM'                , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_PROD_MQ_PORT_NUMBER'       , 'NÚMERO DE PUERTO EN EL SERVIDOR MQ - PRODUCCIÓN'              , '1414'                          , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_PROD_MQ_MANAGER_NAME'      , 'NOMBRE DEL SERVICIO MQ DE LA RENIEC - PRODUCCIÓN'             , 'RENIECQM'                      , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_PROD_MQ_QUEUE_REQUEST'     , 'NOMBRE DE LA COLA DE REQUERIMIENTO - PRODUCCIÓN'              , 'CENTRADA.MIDIS'                , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_PROD_MQ_QUEUE_RESPONSE'    , 'NOMBRE DE LA COLA DE RESPUESTA - PRODUCCIÓN'                  , 'CRESPUESTA.MIDIS'              , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_PROD_MQ_EXPIRATION_TIME'   , 'TIEMPO DE EXPIRACIÓN (Milisegundos) - PRODUCCIÓN'             , '5000'                          , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_PROD_MQ_INSTITUTION_CODE'  , 'CÓDIGO DE LA INSTITUCIÓN ADMITIDA PARA CONSULTAS - PRODUCCIÓN', 'LD2068'                        , 1, 1, 0);
                                                                                                                                                                       
INSERT INTO `grl_parameter` VALUES ('APP_MQ_ENVIRONMENTQUERY_ACTIVE', 'AMBIENTE MQ DE CONSULTAS ACTIVO (0: Producción, 1:Desarrollo)', '0'                             , 1, 1, 0);

INSERT INTO `grl_parameter` VALUES ('APP_PROXY_SET'                 , 'INDICADOR PARA USAR AUTENTICACIÓN AL PROXY (0: No, 1: Si)'    , '1'                             , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_PROXY_HOST'                , 'IP DEL SERVIDOR PROXY'                                        , 'proxy.midis.gob.pe'            , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_PROXY_PORT'                , 'NÚMERO DE PUERTO EN EL SERVIDOR PROXY'                        , '8080'                          , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_PROXY_USER'                , 'USUARIO DE AUTENTICACIÓN PARA PROXY'                          , 'MIDIS\\ordsisfoh'              , 1, 1, 0);
INSERT INTO `grl_parameter` VALUES ('APP_PROXY_PASSWORD'            , 'CONTRASEÑA DE AUTENTICACIÓN PARA PROXY'                       , 'midis123'                      , 1, 1, 0);


-- TABLE grl_user
-- ----------------------------------------------------------------------
CREATE TABLE `grl_user` (
	`dni`				TEXT NOT NULL UNIQUE,
	`ap_first`			TEXT NOT NULL UNIQUE,
	`ap_last`			TEXT NOT NULL UNIQUE,
	`names`				TEXT NOT NULL UNIQUE,
	`user`				TEXT NOT NULL UNIQUE,
	`password`			TEXT NOT NULL,
	`birth_date`		TEXT NOT NULL UNIQUE,
	`max_quota`			INTEGER NOT NULL DEFAULT 0,
	`expiration_date`	TEXT NOT NULL,
	`enabled`			NUMERIC NOT NULL DEFAULT 1,
	PRIMARY KEY(dni)
);
INSERT INTO `grl_user` VALUES ('00000000', 'ADMINISTRADOR', 'ADMINISTRADOR', 'ADMINISTRADOR'    , 'admin'   , '88221279B5DD8F6F', '99/99/9999',  0, '99/99/9999', 1);
INSERT INTO `grl_user` VALUES ('10808968', 'BENAVIDES'    , 'VIZCARRA'     , 'CLAUDIA FABIOLA'  , '10808968', 'D6262A8A6F48511F', '10/12/1977', 10, '31/12/2016', 1);
INSERT INTO `grl_user` VALUES ('42077317', 'CORDOVA'      , 'CORDOVA'      , 'RICARDO'          , '42077317', '29F97B37499F391F', '14/02/1983', 10, '31/12/2016', 1);
INSERT INTO `grl_user` VALUES ('42716939', 'ANGELES'      , 'ROSALES'      , 'MIRKO JIM'        , '42716939', '893928701C8A92CD', '28/09/1984', 10, '31/12/2016', 1);
INSERT INTO `grl_user` VALUES ('40538336', 'ROJAS'        , 'VALENTINO'    , 'ANTONI SASHA'     , '40538336', 'FC998C2826B5A455', '13/01/1980', 10, '31/12/2016', 1);
INSERT INTO `grl_user` VALUES ('09803918', 'COLLANTES'    , 'ODAR'         , 'CLARK'            , '09803918', '566F3F0DBAA1DFA7', '06/06/1971', 10, '31/12/2016', 1);
INSERT INTO `grl_user` VALUES ('41466664', 'DELGADO'      , 'SUAREZ'       , 'GUILLERMO'        , '41466664', 'BC6B26C813514666', '01/07/1982', 10, '31/12/2016', 1);
INSERT INTO `grl_user` VALUES ('09434973', 'CARREÑO'      , 'SOTELO'       , 'VÍCTOR MARTÍN'    , '09434973', '68734F4B383A27D9', '30/11/1968', 10, '31/12/2016', 1);
INSERT INTO `grl_user` VALUES ('08715701', 'DESARROLLO'   , 'DESARROLLO'   , 'DESARROLLO RENIEC', '08715701', 'B5599ECDCAA54EB6', '10/01/2016', 10, '31/12/2016', 1);

-- TABLE grl_audit_log
-- ----------------------------------------------------------------------
CREATE TABLE `grl_audit_log` (
	`id`			INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	`event`			TEXT NOT NULL,
	`user_name`		TEXT NOT NULL,
	`event_date`	TEXT NOT NULL,
	`description`	TEXT NOT NULL,
	`user_os`	TEXT NOT NULL,
	`ip`			TEXT NOT NULL
);
/*	-- Events
	LOGIN
	LOGOUT
	QUERYBYADULTID
	QUERYBYNAMES
	QUERYBYCHILDID
	QUERYBYBATCH
	CONSULTA MASIVA
	QUERYPERSON
	CREATE
	DELETE
	EDIT
	EXPORT
*/

