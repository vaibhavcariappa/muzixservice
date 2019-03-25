import { AppPage } from './app.po';
import { browser, by, element, logging } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title of application', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MuzixApplication');
  });

  it('should be able to open Logo of the application', () => {
    browser.driver.manage().window().maximize();    
    browser.driver.sleep(1000);
    browser.element(by.css('.logo')).click();
    browser.driver.sleep(5000);
    expect(browser.getCurrentUrl()).toContain('/Logo');
    browser.driver.sleep(1000);
  }); 

  it('should be able to click on Menu item for Australia', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-australia')).click();
    expect(browser.getCurrentUrl()).toContain('/Australia');
    browser.driver.sleep(1000);
  });

  it('should be able to save Australia track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  });  

  it('should be able to click on Menu item for Canada', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-canada')).click();
    expect(browser.getCurrentUrl()).toContain('/Canada');
    browser.driver.sleep(1000);
  });

  it('should be able to save Canada track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  }); 

  it('should be able to click on Menu item for China', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-china')).click();
    expect(browser.getCurrentUrl()).toContain('/China');
    browser.driver.sleep(1000);
  });

  it('should be able to save China track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  }); 

  it('should be able to click on Menu item for Germany', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-germany')).click();
    expect(browser.getCurrentUrl()).toContain('/Germany');
    browser.driver.sleep(1000);
  });

  it('should be able to save Germany track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  }); 

  it('should be able to click on Menu item for India', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-india')).click();
    expect(browser.getCurrentUrl()).toContain('/India');
    browser.driver.sleep(1000);
  });

  it('should be able to save India track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  });


  it('should be able to click on Menu item for Ireland', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-ireland')).click();
    expect(browser.getCurrentUrl()).toContain('/Ireland');
    browser.driver.sleep(1000);
  });

  it('should be able to save Ireland track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  }); 

  it('should be able to click on Menu item for Italy', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-italy')).click();
    expect(browser.getCurrentUrl()).toContain('/Italy');
    browser.driver.sleep(1000);
  });

  it('should be able to save Italy track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  }); 

  it('should be able to click on Menu item for Japan', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-japan')).click();
    expect(browser.getCurrentUrl()).toContain('/Japan');
    browser.driver.sleep(1000);
  });

  it('should be able to save Japan track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  }); 

  it('should be able to click on Menu item for Spain', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-spain')).click();
    expect(browser.getCurrentUrl()).toContain('/Spain');
    browser.driver.sleep(1000);
  });

  it('should be able to save Spain track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  }); 

  it('should be able to click on Menu item for Sweden', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-sweden')).click();
    expect(browser.getCurrentUrl()).toContain('/Sweden');
    browser.driver.sleep(1000);
  });

  it('should be able to save Sweden track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  }); 

  it('should be able to click on Menu item for Ukraine', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-ukraine')).click();
    expect(browser.getCurrentUrl()).toContain('/Ukraine');
    browser.driver.sleep(1000);
  });

  it('should be able to save Ukraine track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  }); 

  it('should be able to click on Menu item for United States', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-menu-item-unitedstates')).click();
    expect(browser.getCurrentUrl()).toContain('/UnitedStates');
    browser.driver.sleep(1000);
  });

  it('should be able to save United States track to WishList', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  }); 

  it('should be able to get all data from WishList', () => {
    browser.driver.sleep(1000);
    browser.element(by.css('.wishlist-button')).click();
    expect(browser.getCurrentUrl()).toContain('/WishList');
    browser.driver.sleep(1000);
  });  

  it('should be able to delete track from WishList', () => {
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(1000);
    browser.element(by.css('.deletebutton')).click();
    browser.driver.sleep(1000);
  }); 

  it('should be able to open dialog box to update comments for a track in WishList', () => {
    browser.driver.sleep(500);
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(500);
    browser.element(by.css('.updatebutton')).click();
    browser.driver.sleep(1000);
  });  
  
  it('should be able to save updated comments for a track in WishList', () => {
    browser.driver.sleep(500);
    browser.element(by.css(".matInput")).sendKeys("New Comments for Update!");   
    browser.driver.sleep(500);
    browser.element(by.css('.updateCommentdemo')).click();
    browser.driver.sleep(1000);
  });    

});
