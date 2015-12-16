package jvm;

import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MetaSpace
{
    private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><metaSpace test=\"test\" />";

    @XmlAttribute
    String test;

    public static void main( String[] args ) throws JAXBException, InterruptedException
    {
        System.out.println("start");

        while ( true )
        {
            JAXBContext jc = JAXBContext.newInstance( MetaSpace.class );
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            MetaSpace object = (MetaSpace) unmarshaller.unmarshal( new ByteArrayInputStream( XML.getBytes() ) );
            System.out.println( object.test );
        }
    }
}
