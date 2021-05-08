package com.podong.game.module.schduling.batched;

import com.podong.game.module.schduling.batch.Batch;
import com.podong.game.module.schduling.bean.GameCompanyVO;
import com.podong.game.module.schduling.bean.GameDataVO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class BasicClass implements Batch {
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
    public static final String WEB_DRIVER_PATH = "//Users//podonghi//Downloads//chromedriver"; //드라이버 경로

    @Override
    public void start(){}

    public WebDriver chromeDriver(String gameUrl){
        try {
            System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ChromeOptions options = new ChromeOptions();
        //크롬창 띄울지 말지 결정
        //options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);
        //크롬으로 크롤링할 페이지 Url 삽입
        driver.get(gameUrl);
        // 페이지 로드되는시간 1초 기다리기.
        try {Thread.sleep(1000);} catch (InterruptedException e) {}
        return driver;
    }
    /**
     * Author : po dong hee
     * Date : 2021-04-21
     * Description : Game 에서 사용할 값 셋팅 해주는함수.
     * Param : imgUrl = "이미지주소 : https://cdn.gamemeca.com/gmdb/g001/22/84/gm662247_210421-risell-uu4.jpg"
     *         imgName =  '이미지가 저장될떄 게임명'
     *         uploadUrl =  이미지를 업로드할 경로
     * */
    public void imgDownload(String imgUrl ,String imgName , String uploadUrl){
        BufferedImage jpg = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(imgUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Referer", imgUrl);
            jpg = ImageIO.read(conn.getInputStream());
            //imgName 에 / 가 들어가 있을경우 디렉토리로 인식하여 오류발생.
            imgName =imgName.replaceAll("/","");
            FileOutputStream out = new FileOutputStream(uploadUrl + "/" + imgName+ ".jpg");
            ImageIO.write(jpg, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Author : po dong hee
     * Date : 2021-04-21
     * Description : Game 에서 사용할 값 셋팅 해주는함수.
     * Param : key = "플랫폼" value : 'PC'  vo : 셋팅할 GameDataVO
     * */
    public void switchGame(String key , String value , GameDataVO vo){
        switch (key){
            case "플랫폼" : vo.setPlatform(value);
                break;
            case "장르" : vo.setGenre(value);
                break;
            case "제작사" : vo.setProducer(value);
                break;
            case "제공업체" : vo.setProvider(value);
                break;
            case "이용등급" : vo.setUseCount(value);
                break;
            case "출시년도" : vo.setYearRelase(value);
                break;
            case "게임설명" : vo.setDescription(value);
                break;
        }
    }
    /**
     * Author : po dong hee
     * Date : 2021-04-21
     * Description : Game Company 에서 사용할 값 셋팅 해주는함수.
     * Param : 예시 ) key = "업체명" value : '네오플'  vo : 셋팅할 GameCompanyVO
     * */
    public void switchGameCompany(String key, String value, GameCompanyVO vo){
        switch (key){
            case "업체명" : vo.setGameCompanyName(value);
                break;
            case "대표전화" : vo.setGameCompanyTel(value);
                break;
            case "주소" : vo.setGameCompanyAddr(value);
                break;
            case "대표사이트" : vo.setGameCompanySite(value);
                break;
            case "이미지" : vo.setGameCompanyImg(value);
                break;
        }
    }
}
