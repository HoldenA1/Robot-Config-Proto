package org.usfirst.frc.team972.util;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XMLReader {
	String homeDir = "/home/lvuser/config";
	String filename;
	
	Document dom;
	DocumentBuilderFactory dbf;
	File robotConfig;
	
	/**
	 * @param filename This is the name of the config file (minus any extensions)
	 */
	public XMLReader(String filename) {
		this.filename = filename;
		
		new File(homeDir).mkdir();
		robotConfig = new File(homeDir + "/" + filename + ".xml");
		
		// instance of a DocumentBuilderFactory
	    dbf = DocumentBuilderFactory.newInstance();
		
		try {
			// create files if they don't exist
			if (!robotConfig.exists()) {
				robotConfig.createNewFile();
				// sets up the XML file
				appendXML(RobotSettings.DRIVE_MOTOR_TYPE, RobotSettings.DRIVE_MOTOR_TYPE.getDefualt());
				new File(homeDir + "/" + filename + ".dtd").createNewFile();
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter(homeDir + "/" + filename + ".dtd", true)));
				
				out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				String temp = "<!ELEMENT config (";
				for (RobotSettings setting: RobotSettings.values()) {
					temp = temp + setting.getTag() + ",";	
				}
				temp = temp.replace(temp.substring(temp.length()-1), "");
				out.println(temp + ")>");
				for (RobotSettings setting: RobotSettings.values()) {
					out.println("<!ELEMENT " + setting.getTag() + " (#PCDATA)>");	
				}
				out.close();
				
				System.out.println("PLEASE SET UP CONFIG FILE!!");
				
			}
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		
	}
	
	/**
	 * @param tag The name of the setting you wish to access
	 * @return Returns the value of requested setting in string form
	 */
	public String parseXML(RobotSettings tag) {
		dom = null;
		// Make an instance of the DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// use the factory to take an instance of the document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the
			// XML file
			dom = db.parse(homeDir + "/" + filename + ".xml");

			Element doc = dom.getDocumentElement();
			
			String temp = getTextValue(tag.getTag(), doc, tag.getTag());
			if (temp != null) {
				if (!temp.isEmpty())
					return temp;
			}

		} catch (ParserConfigurationException pce) {
			System.out.println(pce.getMessage());
		} catch (SAXException se) {
			System.out.println(se.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		
		return "Error!! This should never be printed!";
	}
	
	/**
	 * Changes a value in the settings.xml file
	 * @param tag The setting you would like to change
	 * @param value The value you want to set it to (In a later version they will be enums, not strings)
	 */
	public void appendXML(RobotSettings tag, String value) {
		dom = null;
	    Element e = null;

	    // instance of a DocumentBuilderFactory
	    dbf = DocumentBuilderFactory.newInstance();
	    try {
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();

	        // create the root element
	        Element rootEle = dom.createElement("config");

	        // create data elements and place them under root
	        for (RobotSettings setting: RobotSettings.values()) {
	        	if (setting.equals(tag)) {
	        		e = dom.createElement(setting.getTag());
			        e.appendChild(dom.createTextNode(value));
			        rootEle.appendChild(e);
	        	} else {
	        		e = dom.createElement(setting.getTag());
			        e.appendChild(dom.createTextNode(setting.getDefualt()));
			        rootEle.appendChild(e);
	        	}
	        }

	        dom.appendChild(rootEle);

	        try {
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, filename + ".dtd");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            // send DOM to file
	            tr.transform(new DOMSource(dom), 
	                                 new StreamResult(new FileOutputStream(homeDir + "/" + filename + ".xml")));

	        } catch (TransformerException te) {
	            System.out.println(te.getMessage());
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage());
	        }
	    } catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	    }
	}
	
	private String getTextValue(String def, Element doc, String tag) {
	    String value = def;
	    NodeList nl;
	    nl = doc.getElementsByTagName(tag);
	    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
	        value = nl.item(0).getFirstChild().getNodeValue();
	    }
	    return value;
	}

}
