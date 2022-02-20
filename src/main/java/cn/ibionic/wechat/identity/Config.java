package cn.ibionic.wechat.identity;

/**
 * @author ：Yuho Liu
 * @description：配置中心
 * @date ：2021/11/20 5:45 PM
 */
public interface Config {

    String JD_SERVER_URL = "https://api.jd.com/routerjson";

    String JD_APP_KEY = "";

    String JD_APP_SECRET = "";

    String JD_ACCESS_TOKEN = "";

    String OPTION_HELP = "\n🌟🌟\n回复0提现；\n回复1查看可提现金额；\n回复2查看京东未结算订单；\n回复3查看使用说明";

    String HELP_TEXT = "欢迎关注本返利公众号，本公众号承诺全网最高返利。\n需注意，展示的返利金额为商品预售价的基础上，如果实际下单金额更低，佣金也将相应按比例减少\n使用方式：\n发送京东或淘宝APP分享的原生链接到公众号，使用回复的消息中的链接完成后五即可获得奖励(目前京东支持内部优惠券+返利提现，淘宝仅支持内部优惠券)" + OPTION_HELP;

    String TAOBAO_SERVER_URL = "http://gw.api.taobao.com/router/rest";

    String TAOBAO_APP_KEY = "";

    String TAOBAO_APP_SECRET = "";

    Double GLOBAL_COEFFICIENT = 0.8;


}
