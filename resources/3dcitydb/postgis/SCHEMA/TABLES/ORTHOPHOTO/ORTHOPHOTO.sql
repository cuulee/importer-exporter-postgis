-- ORTHOPHOTO.sql
--
-- Authors:     Prof. Dr. Thomas H. Kolbe <kolbe@igg.tu-berlin.de>
--              Prof. Dr. Lutz Pluemer <pluemer@ikg.uni-bonn.de>
--              Dr. Gerhard Groeger <groeger@ikg.uni-bonn.de>
--              Joerg Schmittwilken <schmittwilken@ikg.uni-bonn.de>
--              Viktor Stroh <stroh@ikg.uni-bonn.de>
--              Dr. Andreas Poth <poth@lat-lon.de>
--
-- Conversion:  Laure Fraysse <Laure.fraysse@etumel.univmed.fr>
--              Felix Kunde <felix-kunde@gmx.de>
--
-- Copyright:   (c) 2007-2012  Institute for Geodesy and Geoinformation Science,
--                             Technische Universitšt Berlin, Germany
--                             http://www.igg.tu-berlin.de
--              (c) 2004-2006, Institute for Cartography and Geoinformation,
--                             Universitšt Bonn, Germany
--                             http://www.ikg.uni-bonn.de
--              (c) 2005-2006, lat/lon GmbH, Germany
--                             http://www.lat-lon.de
--
--              This skript is free software under the LGPL Version 2.1.
--              See the GNU Lesser General Public License at
--              http://www.gnu.org/copyleft/lgpl.html
--              for more details.
-------------------------------------------------------------------------------
-- About:
--
--
-------------------------------------------------------------------------------
--
-- ChangeLog:
--
-- Version | Date       | Description      | Author | Conversion
-- 2.0       2012-05-21   PostGIS version    LPlu     LFra
--                                           TKol	  FKun
--                                           GGro
--                                           JSch
--                                           VStr
--                                           APot
--

CREATE TABLE ORTHOPHOTO (
	ID                        SERIAL NOT NULL,
	LOD                       NUMERIC(1,0) NOT NULL,
	NAME                      VARCHAR(256),
	TYPE                      VARCHAR(256),
	DATUM                     DATE,
	ORTHOPHOTOPROPERTY        RASTER
)
;

ALTER TABLE ORTHOPHOTO
ADD CONSTRAINT ORTHOPHOTO_PK PRIMARY KEY
(
ID
)
;