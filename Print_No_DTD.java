import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class Print_No_DTD {
	public static String space = "";
	public static void main(String[] args) throws Exception {
		char[] buffer = new char[100];
		
		Document doc  = getDocument("Ex.xml");
		doc.normalize();
		Element el =doc.getDocumentElement();
		el.normalize();
		System.out.print("Element="+el.getTagName());
		 if(((Node)el).hasAttributes())
		               {
						   NamedNodeMap attrs = el.getAttributes();
						   for ( int j=0; j < attrs.getLength(); j++)
						  {
							  System.out.print(" "+ attrs.item(j).getNodeName()
							  + "="+attrs.item(j).getNodeValue());
						  }

			   }
	    NodeList nodes = el.getChildNodes() ;
	    System.out.println("No of nodes "+ nodes.getLength());
		print_document(nodes,space);
	}
	public static Document getDocument(String file_path) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		//builder.setErrorHandler(new MyErrorHandler());
		Document doc = builder.parse(file_path);
		return doc;
	}
   public static void print_document(NodeList nodes, String indent) throws Exception{
	   int len = nodes.getLength();
	   System.out.println("No of child nodes "+len);
	   for(int i = 0; i <len; i++)
	   {
		   Node child = nodes.item(i);
		   String name = child.getNodeName();
		   String value = child.getNodeValue();
		   System.out.println("***Node is "+child.getNodeName()+" Value "+child.getNodeValue()+"***");

		   if(child.getNodeType() == Node.ELEMENT_NODE )
		   {
			   System.out.print(indent+ name );
               if(child.hasAttributes())
               {
				   NamedNodeMap attrs = child.getAttributes();
				   for ( int j=0; j < attrs.getLength(); j++)
				  {
					  System.out.print(" "+ attrs.item(j).getNodeName()
					  + "="+attrs.item(j).getNodeValue());
				  }

			   }
			    if(child.hasChildNodes())
			       print_document(child.getChildNodes()," "+indent);
		   }
		   else if(nodes.item(i).getNodeType() == Node.TEXT_NODE
		   && value != null && value.trim().length() >0)
		   {
               System.out.println("Name of non-element=  "+ name +" and Value= "+value.trim());
               System.out.println(indent+" = "+value.trim());
		   }
		   else
		       System.out.println(indent);
	   }
   }
}