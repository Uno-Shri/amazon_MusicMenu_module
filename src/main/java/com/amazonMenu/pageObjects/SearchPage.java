package com.amazonMenu.pageObjects;

import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;




public class SearchPage 
{
	
	WebDriver ldriver;
	
	public SearchPage(WebDriver rdriver)
	
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);	
	}
	
	
	//---------------for Hamburgermenu --------------------//
	@FindBy(xpath="//i[@class='hm-icon nav-sprite']") 
	@CacheLookup
    WebElement HamMenu;
	
	public void HamMenu()
	{ 
		HamMenu.click();
	}
	
	
	//-------------for- Amazon Music-------------------//
	@FindBy(xpath="//div[normalize-space()='Amazon Music']") 
	@CacheLookup
    WebElement AmzMusic;
	
	public void AmzMusic()
	{ 
		AmzMusic.click();
	}
	
	//---------for- verify title stream music is present----------//
	
		@FindBy(xpath="//div[normalize-space()='stream music']") 
		@CacheLookup
	    WebElement titleElement;
		public void verifyTitle()
		{	
		String actualTitle=titleElement.getText();
		System.out.println("title is \t" +actualTitle);
		String expectedTitle="Stream Music";
		Assert.assertEquals(actualTitle, expectedTitle);
		
		System.out.println("Stream Music title is verified");
		
	}
	
	
	
	//------------ for- Pod cast----------------------//
  //@FindBy(xpath="//a[normalize-space()='Podcasts']")
	@FindBy(xpath=("//a[contains(text(),'Podcasts')]"))
	@CacheLookup
    WebElement Podcasts;
	
	public void PodcastsClick()
	{ 
		Podcasts.click();
	}
	
	
	//--------------for ListenNow--------------//
	@FindBy(xpath="//div[@id='3']//span[@aria-label='Sign-up link'][normalize-space()='Listen now']") 
	@CacheLookup
    WebElement ListenNow;
	public void ListenNowClick()
	{ 
		ListenNow.click();
	}
	

	//-------------SEDNING the MUSIC NAME FROM CONFIG FILES--------------//
	@FindBy(id="navbarSearchInput") 
	@CacheLookup
    WebElement musicName;
	public void setMusicName(String MName)
	{ 
		musicName.sendKeys(MName);
	}
	
	
	//------------SEARCHING THE MUSIC BY CLICKING SEARCH BTN AT MUSIC--------//
	@FindBy(id="navbarSearchInputButton") 
	@CacheLookup
    WebElement MusicsearchBtn;
	public void MusicSearchbtn()
	{
		MusicsearchBtn.click();
	}
	
	
	
	//--For CLASSIC ROCK -----This Element is inside single shadow DOM---//
	public void PlaylistClick() throws InterruptedException
	{
	String cssSelectorForHost1 = "music-vertical-item[role='gridcell'][class='hydrated'][data-key='Classic Rock Hitsundefined']";
	Thread.sleep(1000);
	SearchContext shadow = ldriver.findElement(By.cssSelector("music-vertical-item[role='gridcell'][class='hydrated'][data-key='Classic Rock Hitsundefined']")).getShadowRoot();
	Thread.sleep(1000);
	shadow.findElement(By.cssSelector(" div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > music-link:nth-child(1) > span:nth-child(1)")).click();
	
	}
	
	
	
	 //-------------MUSIC BAND NAME IS LISTED ---//  

	  public void MusicBand() 
	  {
	   while(true)
		   
	   {
		   List<WebElement> SongsBeforeScrolling =ldriver.findElements(By.xpath("//div[@class='col3']"));
		   System.out.println("\nTotal songs" +SongsBeforeScrolling.size());
		   
		   
		 
		   //scroll to the last song 
		     WebElement lastSong=SongsBeforeScrolling.get(SongsBeforeScrolling.size()-1);
		     int y=lastSong.getLocation().y;
		     JavascriptExecutor js =(JavascriptExecutor)ldriver;
		     js.executeScript("window.scrollTo(0,"+y+")");
		     try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		     List<WebElement> SongsAfterScrolling =ldriver.findElements(By.xpath("//div[@class='col3']"));
	  
		  
		     if (SongsAfterScrolling.size()==SongsBeforeScrolling.size())
	   
	    {
		    	 for (WebElement e:SongsAfterScrolling)
			       {
			    	   System.out.println(e.getText());
			       }
		
		   break;
	    }
		     
	   }
	  }  
}