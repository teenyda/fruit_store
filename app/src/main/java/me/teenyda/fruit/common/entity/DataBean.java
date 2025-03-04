package me.teenyda.fruit.common.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.teenyda.fruit.R;
import me.teenyda.fruit.common.api.Constans;

public class DataBean {
    public Integer imageRes;
    public String imageUrl;
    public String title;
    public int viewType;

    public DataBean(Integer imageRes, String title, int viewType) {
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
    }

    public DataBean(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }

    public static List<DataBean> getTestData2() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.drawable.logo, "听风.赏雨", 3));
        list.add(new DataBean(R.drawable.logo, "迪丽热巴.迪力木拉提", 1));
        list.add(new DataBean(R.drawable.logo, "爱美.人间有之", 3));
        list.add(new DataBean(R.drawable.logo, "洋洋洋.气质篇", 1));
        list.add(new DataBean(R.drawable.logo, "生活的态度", 3));
        return list;
    }

    public static List<Member> getMemberData() {
        List<Member> list = new ArrayList<>();
        list.add(new Member("普通会员", 1, R.mipmap.icon_vip_common, R.drawable.vip1_bg, "15"));
        list.add(new Member("黄金会员", 2, R.mipmap.icon_vip_gold, R.drawable.vip2_bg, "20"));
        list.add(new Member("钻石会员", 3, R.mipmap.icon_vip_diamond, R.drawable.vip3_bg, "25"));
        return list;
    }

    /*public static List<DataBean> getTestData() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.drawable.image1, "相信自己,你努力的样子真的很美", 1));
        list.add(new DataBean(R.drawable.image2, "极致简约,梦幻小屋", 3));
        list.add(new DataBean(R.drawable.image3, "超级卖梦人", 3));
        list.add(new DataBean(R.drawable.image4, "夏季新搭配", 1));
        list.add(new DataBean(R.drawable.image5, "绝美风格搭配", 1));
        list.add(new DataBean(R.drawable.image6, "微微一笑 很倾城", 3));
        return list;
    }

    public static List<DataBean> getTestData2() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.drawable.image7, "听风.赏雨", 3));
        list.add(new DataBean(R.drawable.image8, "迪丽热巴.迪力木拉提", 1));
        list.add(new DataBean(R.drawable.image9, "爱美.人间有之", 3));
        list.add(new DataBean(R.drawable.image10, "洋洋洋.气质篇", 1));
        list.add(new DataBean(R.drawable.image11, "生活的态度", 3));
        return list;
    }

    /*
     * 仿淘宝商品详情第一个是视频
     * @return
     */
    public static List<DataBean> getTestDataVideo() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean("http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4", "第一个放视频", 2));
        list.add(new DataBean(R.drawable.image7, "听风.赏雨", 1));
        list.add(new DataBean(R.drawable.image8, "迪丽热巴.迪力木拉提", 1));
        list.add(new DataBean(R.drawable.image9, "爱美.人间有之", 1));
        list.add(new DataBean(R.drawable.image10, "洋洋洋.气质篇", 1));
        list.add(new DataBean(R.drawable.image11, "生活的态度", 1));
        return list;
    }

    public static List<DataBean> getNewFruitData() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.drawable.new_product_01, "听风.赏雨", 1));
        list.add(new DataBean(R.drawable.new_product_02, "迪丽热巴.迪力木拉提", 1));
        list.add(new DataBean(R.drawable.new_product_03, "爱美.人间有之", 1));
        list.add(new DataBean(R.drawable.new_product_04, "洋洋洋.气质篇", 1));
        return list;
    }

    public static List<DataBean> getTestData3() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean("https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg", null, 1));
        list.add(new DataBean("https://img.zcool.cn/community/01639a56fb62ff6ac725794891960d.jpg", null, 1));
        list.add(new DataBean("https://img.zcool.cn/community/01270156fb62fd6ac72579485aa893.jpg", null, 1));
        list.add(new DataBean("https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg", null, 1));
        list.add(new DataBean("https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg", null, 1));
        list.add(new DataBean(Constans.BaseUrl +  "/file/downloadFile/f28407a9111145708d77d6e2909795b7.jpg", null, 1));
        return list;
    }

    public static List<DataBean> getProductInfoData() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/01.jpg", null, 1));
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/02.jpg", null, 1));
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/03.jpg", null, 1));
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/04.jpg", null, 1));
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/05.jpg", null, 1));
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/06.jpg", null, 1));
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/07.jpg", null, 1));
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/08.jpg", null, 1));
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/09.jpg", null, 1));
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/10.jpg", null, 1));
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/11.jpg", null, 1));
        list.add(new DataBean("http://www.lanhuoxing.cn/uploads/allimg/techanshuiguo/yingtao/meizao/meizao3/12.jpg", null, 1));
        return list;
    }

    public static List<String> getColors(int size) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(getRandColor());
        }
        return list;
    }

    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     *
     * @return String
     */
    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }
}
