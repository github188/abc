package com.jd.pims.util;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class XMLHelperTest {

	XMLHelper helper=null;
	@Test
	public void testGetLastVersion() {
		try {
			helper=new XMLHelper("ios-update.xml");
			String version=helper.getLatestVersion();
			if(version!=null){
				System.out.println("version："+version);
				Assert.assertTrue( version!=null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			fail("Not yet implemented!"+e.getMessage());
		}
		
	}

	@Test
	public void testGetUpdateContent() {
		try {
			helper=new XMLHelper("ios-update.xml");
			String updateContent=helper.getUpdateContent(helper.getLatestVersion());
			if(updateContent!=null){
				System.out.println("updateContent："+updateContent);
				Assert.assertTrue(updateContent!=null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			fail("Not yet implemented!"+e.getMessage());
		}
	}

	@Test
	public void testGetUpdateUrl() {
		try {
			helper=new XMLHelper("ios-update.xml");
			String updateUrl=helper.getUpdateUrl(helper.getLatestVersion());
			if(updateUrl!=null){
				System.out.println("updateUrl："+updateUrl);
				Assert.assertTrue(updateUrl!=null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			fail("Not yet implemented!"+e.getMessage());
		}
	}

}
