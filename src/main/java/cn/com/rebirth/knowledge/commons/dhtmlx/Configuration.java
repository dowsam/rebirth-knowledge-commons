/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Configuration.java 2012-8-3 21:35:45 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.PageSetting;

import com.google.common.collect.Lists;

/**
 * The Class Configuration.
 *
 * @author l.xue.nong
 */
public final class Configuration {

	/** The Constant CONF_GRID_SCRIPT_PATH. */
	public static final String CONF_GRID_SCRIPT_PATH = "grid_script_path";

	/** The Constant CONF_GRID_SERVLET_PATH. */
	public static final String CONF_GRID_SERVLET_PATH = "grid_servlet_path";

	/** The instance. */
	private static Configuration instance = null;

	/** The properties. */
	private Properties properties;

	/** The character encoding. */
	private String characterEncoding = "UTF-8";

	/** The page setting. */
	private PageSetting pageSetting;

	/** The default grid. */
	private GridType defaultGrid = GridType.dhtmlxGrid;

	/** The data querier. */
	private String dataQuerier;

	/** The data exporter. */
	private String dataExporter;

	/** The export excel path. */
	private String exportExcelPath;

	/** The buildin grid. */
	private List<BuildinGrid> buildinGrid = new ArrayList<BuildinGrid>();

	/** The cached buildin grid. */
	private static Map<String, BuildinGrid> cachedBuildinGrid = new HashMap<String, BuildinGrid>();

	private Configuration() {
		super();
		PageSetting pageSetting = new PageSetting();
		pageSetting.setEnabled(true);
		pageSetting.setSize(10);
		pageSetting.setSizeList("10,20,30,40");
		pageSetting.setStyle("default");
		setPageSetting(pageSetting);
		BuildinGrid buildinGrid2 = new BuildinGrid();
		buildinGrid2.setName("dhtmlxgrid");
		buildinGrid2.setBuilder("dhtmlxgridBuilder");
		buildinGrid2.setRender("dhtmlxgridRender");
		setBuildinGrid(Lists.newArrayList(buildinGrid2));
		for (BuildinGrid b : getBuildinGrid()) {
			Configuration.cachedBuildinGrid.put(b.getName(), b);
		}
	}

	/**
	 * Gets the single instance of Configuration.
	 *
	 * @return single instance of Configuration
	 */
	public synchronized static Configuration getInstance() {
		if (null == instance) {
			instance = new Configuration();
		}
		return instance;
	}

	/**
	 * Gets the data querier.
	 *
	 * @return the data querier
	 */
	public String getDataQuerier() {
		return dataQuerier;
	}

	/**
	 * Sets the data querier.
	 *
	 * @param dataQuerier the new data querier
	 */
	public void setDataQuerier(String dataQuerier) {
		this.dataQuerier = dataQuerier;
	}

	/**
	 * Gets the default grid.
	 *
	 * @return the default grid
	 */
	public GridType getDefaultGrid() {
		return defaultGrid;
	}

	/**
	 * Gets the data exporter.
	 *
	 * @return the data exporter
	 */
	public String getDataExporter() {
		return dataExporter;
	}

	/**
	 * Sets the data exporter.
	 *
	 * @param dataExporter the new data exporter
	 */
	public void setDataExporter(String dataExporter) {
		this.dataExporter = dataExporter;
	}

	/**
	 * Gets the buildin grid.
	 *
	 * @return the buildin grid
	 */
	public List<BuildinGrid> getBuildinGrid() {
		return buildinGrid;
	}

	/**
	 * Sets the buildin grid.
	 *
	 * @param buildinGrid the new buildin grid
	 */
	public void setBuildinGrid(List<BuildinGrid> buildinGrid) {
		this.buildinGrid = buildinGrid;
	}

	/**
	 * Sets the default grid.
	 *
	 * @param defaultGrid the new default grid
	 */
	public void setDefaultGrid(GridType defaultGrid) {
		this.defaultGrid = defaultGrid;
	}

	/**
	 * Gets the page setting.
	 *
	 * @return the page setting
	 */
	public PageSetting getPageSetting() {
		return pageSetting;
	}

	/**
	 * Sets the page setting.
	 *
	 * @param pageSetting the new page setting
	 */
	public void setPageSetting(PageSetting pageSetting) {
		this.pageSetting = pageSetting;
	}

	/**
	 * Gets the character encoding.
	 *
	 * @return the character encoding
	 */
	public String getCharacterEncoding() {
		return characterEncoding;
	}

	/**
	 * Gets the export excel path.
	 *
	 * @return the export excel path
	 */
	public String getExportExcelPath() {
		return exportExcelPath;
	}

	/**
	 * Sets the export excel path.
	 *
	 * @param exportExcelPath the new export excel path
	 */
	public void setExportExcelPath(String exportExcelPath) {
		this.exportExcelPath = exportExcelPath;
	}

	/**
	 * Sets the character encoding.
	 *
	 * @param characterEncoding the new character encoding
	 */
	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	/**
	 * Gets the property.
	 *
	 * @param configKey the config key
	 * @return the property
	 */
	public String getProperty(String configKey) {
		if (getProperties().containsKey(configKey)) {
			return getProperties().getProperty(configKey);
		} else {
			return System.getProperty(configKey);
		}
	}

	/**
	 * Sets the property.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void setProperty(String key, String value) {
		getProperties().setProperty(key, value);
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	private Properties getProperties() {
		if (null == properties) {
			properties = new Properties();
		}
		return properties;
	}

	/**
	 * Gets the builin grid.
	 *
	 * @param gridTypeName the grid type name
	 * @return the builin grid
	 */
	public BuildinGrid getBuilinGrid(String gridTypeName) {
		if (cachedBuildinGrid.containsKey(gridTypeName)) {
			return cachedBuildinGrid.get(gridTypeName);
		}
		BuildinGrid buildinGrid = new BuildinGrid();
		String buildinGridName = GridType.dhtmlxGrid.name();
		buildinGrid.setName(buildinGridName);
		buildinGrid.setBuilder(buildinGridName + "Builder");
		buildinGrid.setRender(buildinGridName + "Render");
		return buildinGrid;
	}

	/**
	 * Sets the properties.
	 *
	 * @param properties the new properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/*
	public static void main(String[] args) {
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		Configuration config = new Configuration();
		config.setCharacterEncoding("UTF-8");
		config.setDataQuerier("KissU");
		config.setProperty("loadOnFirest", "false");
		PageSetting pageSetting = new PageSetting();
		pageSetting.setEnabled(true);
		pageSetting.setSize(10);
		pageSetting.setSizeList("10,20,30,40");
		pageSetting.setStyle("default");
		config.setPageSetting(pageSetting);
		BuildinGrid buildinGrid = config.new BuildinGrid();
		buildinGrid.setName("dhtmlxgrid");
		buildinGrid.setBuilder("dhtmlxgridBuilder");
		buildinGrid.setRender("dhtmlxgridRender");
		buildinGrid.setProperty("imagePath", "c:\\image\\");
		config.getBuildinGrid().add(buildinGrid);
		System.out.println(xstream.toXML(config));
	}*/

	/**
	 * The Class BuildinGrid.
	 *
	 * @author l.xue.nong
	 */
	public class BuildinGrid {

		/** The name. */
		private String name;

		/** The builder. */
		private String builder;

		/** The render. */
		private String render;

		/** The properties. */
		private Properties properties = new Properties();

		/**
		 * Gets the name.
		 *
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * Sets the name.
		 *
		 * @param name the new name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * Gets the builder.
		 *
		 * @return the builder
		 */
		public String getBuilder() {
			return builder;
		}

		/**
		 * Sets the builder.
		 *
		 * @param builder the new builder
		 */
		public void setBuilder(String builder) {
			this.builder = builder;
		}

		/**
		 * Gets the render.
		 *
		 * @return the render
		 */
		public String getRender() {
			return render;
		}

		/**
		 * Sets the render.
		 *
		 * @param render the new render
		 */
		public void setRender(String render) {
			this.render = render;
		}

		/**
		 * Gets the properties.
		 *
		 * @return the properties
		 */
		public Properties getProperties() {
			return properties;
		}

		/**
		 * Sets the properties.
		 *
		 * @param properties the new properties
		 */
		public void setProperties(Properties properties) {
			this.properties = properties;
		}

		/**
		 * Sets the property.
		 *
		 * @param name the name
		 * @param value the value
		 */
		public void setProperty(String name, String value) {
			this.properties.setProperty(name, value);
		}

		/**
		 * Gets the property.
		 *
		 * @param name the name
		 * @return the property
		 */
		public String getProperty(String name) {
			return this.properties.getProperty(name);
		}
	}
}