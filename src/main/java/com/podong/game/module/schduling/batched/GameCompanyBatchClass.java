package com.podong.game.module.schduling.batched;

import com.podong.game.module.schduling.bean.GameCompanyVO;
import com.podong.game.module.schduling.dao.BatchMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("gameCompanyBatchAdptee")
public class GameCompanyBatchClass extends BasicClass {

    @Value("${repository.uploadCompanyImg}")
    private String uploadCompanyUrl;

    @Value("${crawling.gameCompanyUrl}")
    private String gameCompanyUrl;

    @Autowired
    private BatchMapper batchMapper;

    public void gameCompanyBatch(){
        try {
            GameCompanyVO vo = new GameCompanyVO();
            Document doc = null;
            Elements elCnt = null;
            doc = Jsoup.connect(gameCompanyUrl).get();
            elCnt = doc.select("div[class=\"dbnum h2 mRight\"]");
            String strCnt = elCnt.text().substring(1,8).replace(",","").trim();
            if(strCnt.substring(4,5).equals("개"))
            {
                strCnt = strCnt.replace("개","");
            }
            int nextCnt;
            int totalCnt = Integer.parseInt(strCnt);
            if(totalCnt % 48 == 0)
            {
                nextCnt = totalCnt / 48;
            }
            else
            {
                nextCnt = (totalCnt / 48) + 1;
            }
            String aTag = null;
            String gmCid = null;
            String imgUrl = null;
            Elements elm = null;
            for(int i=1; i < nextCnt; i ++) {
                doc = Jsoup.connect(gameCompanyUrl+"&p=" + i).get();
                elm = doc.select("div[class=\"company-list \"]");
                for (Element data : elm) {
                    for (Element list : data.select("ul > li")) {
                        aTag =  list.select("a").attr("href");
                        gmCid = aTag.replace("/game.php?rts=company&gmcid=","");
                        imgUrl = list.select("img").attr("src");
                        vo.setGameCompanyName(list.getElementsByClass("tit_name").text());
                        vo.setGameCompanyCid(gmCid);
                        imgDownload(imgUrl, vo.getGameCompanyName(), uploadCompanyUrl);
                        batchMapper.insertGameCompany(vo);
                        System.out.println("******  성공한 데이터 CompanyCid : "+ vo.getGameCompanyCid() + " CompanyName : "+ vo.getGameCompanyName()+"  ******");

                    }
                }

            }
            System.out.println("GAME COMPANY LIST 가져오기");
            List<GameCompanyVO> companyList = batchMapper.getCompanyTotalList();
            for(GameCompanyVO coVo : companyList){
                doc = Jsoup.connect(gameCompanyUrl + "&gmcid=" +coVo.getGameCompanyCid()).get();
                elm = doc.select("div[class=\"topViewBox\"]");
                for (Element data : elm) {
                    for (Element list : data.select("p")) {
                        switchGameCompany(list.getElementsByClass("leftA").text(), list.getElementsByClass("rightA").text(), vo);
                    }
                    vo.setGameCompanyCid(coVo.getGameCompanyCid());
                    batchMapper.updateGameCompany(vo);
                    System.out.println("게임업체 명 : "+ vo.getGameCompanyName() +" 게입업체 고유번호 :" + vo.getGameCompanyCid() );
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void start() {
        gameCompanyBatch();
    }

}
