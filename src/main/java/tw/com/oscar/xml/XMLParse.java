/**
 * XMLParse.java
 * Title: DTS Project
 * Copyright: Copyright(c)2015, Acer
 *
 * @author Oscar Wei
 * @since 2015/7/24
 * <p>
 * H i s t o r y
 * 2015/7/24 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.xml;

import com.google.common.base.MoreObjects;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Title: XMLParse.java
 * </p>
 * <strong>Description:</strong> A sample for parsing XML file <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/24
 * @since 2015/7/24
 */
public class XMLParse {

    public static void main(String[] args) throws Exception {
        File xmlFile = new File("DBConf.xml");
        parse(xmlFile);
    }

    public static Set<Datasource> parse(File file) throws Exception {
        if (null == file) {
            throw new IllegalArgumentException("Illegal argument passed: File cannot be null");
        }
        Set<Datasource> dataSources = new HashSet<>();
        Datasource ds;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("datasource");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                ds = new Datasource();
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String jdbcDriver = element.getElementsByTagName("jdbc-driver").item(0).getTextContent();
                NodeList hostsNodeList = element.getElementsByTagName("hosts");
                List<String> hosts = new LinkedList<>();
                for (int j = 0; j < hostsNodeList.getLength(); j++) {
                    Node hostNode = hostsNodeList.item(j);
                    Element hostElement = (Element) hostNode;
                    NodeList hostNodeList = hostElement.getElementsByTagName("host");
                    for (int k = 0; k < hostNodeList.getLength(); k++) {
                        hosts.add(hostNodeList.item(k).getTextContent());
                    }
                }
                ds.setHosts(hosts);
                String port = element.getElementsByTagName("port").item(0).getTextContent();
                String serviceName = element.getElementsByTagName("service-name").item(0).getTextContent();
                String username = element.getElementsByTagName("user-name").item(0).getTextContent();
                String password = element.getElementsByTagName("password").item(0).getTextContent();
                ds.setId(id);
                ds.setJdbcDriver(jdbcDriver);
                ds.setPort(Integer.parseInt(port));
                ds.setServiceName(serviceName);
                ds.setUsername(username);
                ds.setPassword(password);
                System.out.println(ds);
                dataSources.add(ds);
            }
        }
        return dataSources;
    }

    private static class Datasource {

        private String id;
        private String jdbcDriver;
        private List<String> hosts = new LinkedList<>();
        private Integer port;
        private String serviceName;
        private String username;
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJdbcDriver() {
            return jdbcDriver;
        }

        public void setJdbcDriver(String jdbcDriver) {
            this.jdbcDriver = jdbcDriver;
        }

        public List<String> getHosts() {
            return hosts;
        }

        public void setHosts(List<String> hosts) {
            this.hosts = hosts;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("id", this.getId())
                    .add("jdbcDriver", this.getJdbcDriver())
                    .add("hosts", this.getHosts())
                    .add("port", this.getPort())
                    .add("serviceName", this.getServiceName())
                    .add("username", this.getUsername())
                    .add("password", this.getPassword())
                    .toString();
        }
    }
}
