package de.tub.citydb.db.xlink.importer;

import java.sql.SQLException;
import java.util.HashMap;

import de.tub.citydb.db.cache.CacheManager;
import de.tub.citydb.db.cache.TemporaryCacheTable;
import de.tub.citydb.db.cache.model.CacheTableModelEnum;
import de.tub.citydb.event.Event;
import de.tub.citydb.event.EventDispatcher;

public class DBXlinkImporterManager {
	private final CacheManager dbTempTableManager;
	private final EventDispatcher eventDispatcher;
	private HashMap<DBXlinkImporterEnum, DBXlinkImporter> dbImporterMap;

	public DBXlinkImporterManager(CacheManager dbTempTableManager, EventDispatcher eventDispatcher) {
		this.dbTempTableManager = dbTempTableManager;
		this.eventDispatcher = eventDispatcher;

		dbImporterMap = new HashMap<DBXlinkImporterEnum, DBXlinkImporter>();
	}

	public DBXlinkImporter getDBImporterXlink(DBXlinkImporterEnum xlinkType) throws SQLException {
		DBXlinkImporter dbImporter = dbImporterMap.get(xlinkType);

		if (dbImporter == null) {
			// firstly create tmp table
			TemporaryCacheTable tempTable = null;

			switch (xlinkType) {
			case SURFACE_GEOMETRY:
				tempTable = dbTempTableManager.createTemporaryCacheTable(CacheTableModelEnum.SURFACE_GEOMETRY);
				break;
			case LINEAR_RING:
				tempTable = dbTempTableManager.createTemporaryCacheTable(CacheTableModelEnum.LINEAR_RING);
				break;
			case XLINK_BASIC:
				tempTable = dbTempTableManager.createTemporaryCacheTable(CacheTableModelEnum.BASIC);
				break;
			case XLINK_TEXTUREPARAM:
				tempTable = dbTempTableManager.createTemporaryCacheTable(CacheTableModelEnum.TEXTUREPARAM);
				break;
			case XLINK_TEXTUREASSOCIATION:
				tempTable = dbTempTableManager.createTemporaryCacheTable(CacheTableModelEnum.TEXTUREASSOCIATION);
				break;
			case TEXTURE_FILE:
				tempTable = dbTempTableManager.createTemporaryCacheTable(CacheTableModelEnum.TEXTURE_FILE);
				break;
			case LIBRARY_OBJECT:
				tempTable = dbTempTableManager.createTemporaryCacheTable(CacheTableModelEnum.LIBRARY_OBJECT);
				break;
			case XLINK_DEPRECATED_MATERIAL:
				tempTable = dbTempTableManager.createTemporaryCacheTable(CacheTableModelEnum.DEPRECATED_MATERIAL);
				break;
			case GROUP_TO_CITYOBJECT:
				tempTable = dbTempTableManager.createTemporaryCacheTable(CacheTableModelEnum.GROUP_TO_CITYOBJECT);
				break;
			}

			if (tempTable != null) {
				// initialize DBImporter
				switch (xlinkType) {
				case SURFACE_GEOMETRY:
					dbImporter = new DBXlinkImporterSurfaceGeometry(tempTable);
					break;
				case LINEAR_RING:
					dbImporter = new DBXlinkImporterLinearRing(tempTable);
					break;
				case XLINK_BASIC:
					dbImporter = new DBXlinkImporterBasic(tempTable);
					break;
				case XLINK_TEXTUREPARAM:
					dbImporter = new DBXlinkImporterTextureParam(tempTable);
					break;
				case XLINK_TEXTUREASSOCIATION:
					dbImporter = new DBXlinkImporterTextureAssociation(tempTable);
					break;
				case TEXTURE_FILE:
					dbImporter = new DBXlinkImporterTextureFile(tempTable);
					break;
				case LIBRARY_OBJECT:
					dbImporter = new DBXlinkImporterLibraryObject(tempTable);
					break;
				case XLINK_DEPRECATED_MATERIAL:
					dbImporter = new DBXlinkImporterDeprecatedMaterial(tempTable);
					break;
				case GROUP_TO_CITYOBJECT:
					dbImporter = new DBXlinkImporterGroupToCityObject(tempTable);
					break;
				}

				if (dbImporter != null)
					dbImporterMap.put(xlinkType, dbImporter);
			}
		}

		return dbImporter;
	}

	public void propagateEvent(Event event) {
		eventDispatcher.triggerEvent(event);
	}

	public void executeBatch() throws SQLException {
		for (DBXlinkImporter dbImporter : dbImporterMap.values())
			dbImporter.executeBatch();
	}
	
	public void close() throws SQLException {
		for (DBXlinkImporter dbImporter : dbImporterMap.values())
			dbImporter.close();
	}
}
