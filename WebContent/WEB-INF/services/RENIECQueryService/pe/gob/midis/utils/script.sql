-- TABLE grl_address_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_address_prefix` (
	`code`	TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_address_prefix` VALUES (''  , ''      );
INSERT INTO `grl_address_prefix` VALUES ('01', 'AV.'   );
INSERT INTO `grl_address_prefix` VALUES ('02', 'JR.'   );
INSERT INTO `grl_address_prefix` VALUES ('03', 'CALLE' );
INSERT INTO `grl_address_prefix` VALUES ('04', 'PARQUE');
INSERT INTO `grl_address_prefix` VALUES ('05', 'PSJ.'  );
INSERT INTO `grl_address_prefix` VALUES ('06', 'OVALO' );


-- TABLE grl_block_or_chalet_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_block_or_chalet_prefix` (
	`code`	TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_block_or_chalet_prefix` VALUES ('01',	'BLOCK' );
INSERT INTO `grl_block_or_chalet_prefix` VALUES ('02',	'CHALET');	


-- TABLE grl_dpto_floor_internal_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_dpto_floor_internal_prefix` (
	`code`	TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_dpto_floor_internal_prefix` VALUES ('01',	'DPTO.');
INSERT INTO `grl_dpto_floor_internal_prefix` VALUES ('02',	'EDIF.');
INSERT INTO `grl_dpto_floor_internal_prefix` VALUES ('03',	'INT.' );
INSERT INTO `grl_dpto_floor_internal_prefix` VALUES ('04',	'PISO' );


-- TABLE grl_urb_condom_resid_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_urb_condom_resid_prefix` (
	`code`	TEXT NOT NULL UNIQUE,
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


-- TABLE grl_block_or_chalet_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_restrictions` (
	`code`	TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(code)
);
INSERT INTO `grl_restrictions` VALUES (''   , 'NINGUNA'                             );
INSERT INTO `grl_restrictions` VALUES ('A'  , 'FALLECIMIENTO'                       );
INSERT INTO `grl_restrictions` VALUES ('04' , 'FALLECIMIENTO MENOR'                 );
INSERT INTO `grl_restrictions` VALUES ('R'  , 'RENUNCIA A LA NACIONALIDAD PERUANA'  );
INSERT INTO `grl_restrictions` VALUES ('11' , 'PERDIDA DE NACIONALIDAD'             );
INSERT INTO `grl_restrictions` VALUES ('_'	, 'LEY DEL SERVICIO MILITAR'            );
INSERT INTO `grl_restrictions` VALUES ('«'	, 'DECLARACION JUDICIAL DE INTERDICCION');
INSERT INTO `grl_restrictions` VALUES ('3'	, 'DECLARADO JUDICIALMENTE AUSENTE'     );
INSERT INTO `grl_restrictions` VALUES (')'	, 'DNI S/VIG.EXT.DEL DOM Y ESTCIV.'     );
INSERT INTO `grl_restrictions` VALUES ('['	, 'DOMICILIO FISCALIZADO POR EL JNE'    );
INSERT INTO `grl_restrictions` VALUES (']'	, 'DOMICILIO IMPUGANDO POR EL CIUDADANO');
INSERT INTO `grl_restrictions` VALUES ('{'	, 'DNI S/VIG.EN EL EXT.DEL DOMIC.'      );
INSERT INTO `grl_restrictions` VALUES ('}'	, 'DNI S/VIG.EN EL EXT.DEL ESTCIV.'     );
INSERT INTO `grl_restrictions` VALUES ('¡'	, 'ESTADO CIVIL NO ACTUALIZADO'         );
INSERT INTO `grl_restrictions` VALUES ('¶'	, 'DOMICILIO NO ACTUALIZADO'            );
INSERT INTO `grl_restrictions` VALUES ('Ø'	, 'ESTADO CIVIL Y DOMICILIO IMPUGNADOS' );
INSERT INTO `grl_restrictions` VALUES ('~'	, 'MEDIDA CAUTELAR'                     );


-- TABLE grl_block_or_chalet_prefix
-- ----------------------------------------------------------------------
CREATE TABLE `grl_parameters` (
	`code`	TEXT NOT NULL UNIQUE,
	`description`	TEXT NOT NULL UNIQUE,
	`value`	TEXT NOT NULL,
	`enabled`	NUMERIC NOT NULL DEFAULT 1,
	`visible`	INTEGER NOT NULL DEFAULT 1,
	`removable`	INTEGER DEFAULT 1,
	PRIMARY KEY(code)
);
INSERT INTO `grl_parameters` VALUES ('APP_USER'	    , 'USUARIO DE ACCESO A LA APLICACIÓN'   , 'admin', 1, 0, 0 );
INSERT INTO `grl_parameters` VALUES ('APP_PASSWORD'	, 'CONTRASEÑA DE ACCESO A LA APLICACIÓN', 'admin', 1, 0, 0 );



