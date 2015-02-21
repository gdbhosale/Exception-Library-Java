import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.*;
class XMLParserClass {
static Map tableOptimized;
public static Hashtable getHashtable() //=================================================================get Hashtable
	{
    try	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder        = factory.newDocumentBuilder();
		Document doc 				   = builder.parse("Config.xml");
        Element el = doc.getDocumentElement();
        NodeList nodeListStudent = el.getChildNodes();
        
		Map table = getParsedHashTable(nodeListStudent);
		tableOptimized = optimise((Hashtable)table);
		
        }
    catch(Exception e) 
        {
        System.out.println(e);
        }
	return (Hashtable)tableOptimized;
    }
public static Map getParsedHashTable(NodeList nodes) throws Exception//==========================================Print Document
    {
	Map ht  = new Hashtable();
    int len = nodes.getLength();
	
    for(int i = 0; i <len; i++)
        {
        Node child   = nodes.item(i);
        String name  = child.getNodeName();
        String value = child.getNodeValue();
        Map ht_int   = new Hashtable();
		
        if(child.getNodeType() == Node.ELEMENT_NODE)//if node is Element Node.
            {
            //System.out.print("\n 3."+" "+ name );
			if(child.hasChildNodes())
                {
				ht_int = getParsedHashTable(child.getChildNodes());
				}
            if(child.hasAttributes())						//if child node has a Attributes.
                {
                NamedNodeMap attrs = child.getAttributes();	//get number of Attributes in node
                for(int j=0; j < attrs.getLength(); j++)	//Display number of Attributes in node
                    {
					String nodeName  = attrs.item(j).getNodeName();
					String nodeValue = attrs.item(j).getNodeValue();
                    //System.out.print("\t4. "+ nodeName + "="+nodeValue+" "+j);
                    if(! nodeName.equals("name"))
						ht_int.put(nodeName, nodeValue);
					else
						name = nodeValue;
					}
                }
            }
		ht.put(name, ht_int);
        }
	return ht;
    }
public static Hashtable optimise(Hashtable table)//=======================================================Optimization
	{
	Object obj;
	Hashtable module_Table, exception_Table, action_Table = new Hashtable();
	
	String nameProject, nameModule, nameException;
	
	Set keyProjects = table.keySet();								//set of Project names
	Iterator iter_Projects  = keyProjects.iterator();
	
	while(iter_Projects.hasNext())									//Iterating project hash tables...
			{
			nameProject = (String)iter_Projects.next();				//Project name
			module_Table = (Hashtable)table.get(nameProject);
			
			Set keyModules = module_Table.keySet();					//Set of Module names
			Iterator inter_Modules = keyModules.iterator();
			
			while(inter_Modules.hasNext())							//Iterating Modules hash tables...
					{
					nameModule     = (String)inter_Modules.next();	//Module Name
					exception_Table = (Hashtable)module_Table.get(nameModule);
					
					Set keyException = exception_Table.keySet();		//set of Exception names
					Iterator inter_Exceptions   = keyException.iterator();
					
					while(inter_Exceptions.hasNext())					//Iterating Exceptions hash tables...
							{
							nameException = (String)inter_Exceptions.next();
							String name   = nameProject+"."+nameModule+"."+nameException;
							//System.out.println(name);
							
							action_Table.put(name, (Hashtable)exception_Table.get(nameException));
							//Adding Exception table to action_Table
							}
					}
			}
	return action_Table;
	}
}