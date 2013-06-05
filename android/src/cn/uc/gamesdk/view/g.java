package cn.uc.gamesdk.view;

import android.content.Context;
import android.content.Intent;
import cn.uc.gamesdk.UCUIStyle;
import cn.uc.gamesdk.b.e;
import cn.uc.gamesdk.service.CommonService;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * 
 * @author Rect
 * 2013-5-23
 */
public class g
{
  private static final String p = "pageTitle";
  private static final String q = "pageId";
  public static final String a = "uiBusiness";
  public static final String b = "login";
  public static final String c = "pay";
  public static final String d = "userCenter";
  public static final String e = "h5EntryKey";
  public static final String f = "login";
  public static final String g = "pay";
  public static final String h = "user_center";
  public static final String i = "update_pwd";
  public static final String j = "forget_pwd";
  public static final String k = "login_game";
  public static final String l = "register";
  public static final String m = "alpha_code";
  public static final String n = "game_not_open";
  public static final String o = "nativePageId";

  public static void a(String h5EntryKey, String uiBusiness, int pageId, String pageTitle)
  {
    JSONObject jsonObj = new JSONObject();
    try {
      jsonObj.put("uiBusiness", uiBusiness);
      jsonObj.put("pageId", pageId);
      jsonObj.put("pageTitle", pageTitle);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    CommonService.currentNativeSourcePageInfo = jsonObj;
    a(h5EntryKey, uiBusiness);
  }

  public static void a(int pageId, String uiBusiness)
  {
    CommonService.currentNativeSourcePageInfo = null;
    b(pageId, uiBusiness);
  }

  public static boolean a()
  {
    JSONObject jsonObj = CommonService.currentNativeSourcePageInfo;
    if (jsonObj != null) {
      int pageId = jsonObj.optInt("pageId");
      String uiBusiness = jsonObj.optString("uiBusiness");
      a(pageId, uiBusiness);
      return true;
    }
    return false;
  }

  public static void a(String uiBusiness)
  {
    if ("login".equals(uiBusiness)) {
      if (cn.uc.gamesdk.b.e.s == UCUIStyle.SIMPLE)
        b(1, uiBusiness);
      else {
        a("login", uiBusiness);
      }
      return;
    }

    if ("pay".equals(uiBusiness)) {
      a("pay", uiBusiness);
      return;
    }

    if ("userCenter".equals(uiBusiness)) {
      a("user_center", uiBusiness);
      return;
    }

    a(uiBusiness, uiBusiness);
  }

  private static void a(String h5EntryKey, String uiBusiness)
  {
    Intent intent = new Intent(cn.uc.gamesdk.b.e.b, SdkWebActivity_s.class);
    intent.putExtra("h5EntryKey", h5EntryKey);
    intent.putExtra("uiBusiness", uiBusiness);
    intent.putExtra("activity_work_type", 2);
    int flags = 805306368;
    intent.setFlags(flags);
//    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    cn.uc.gamesdk.b.e.b.startActivity(intent);
  }

  private static void b(int nativePageId, String uiBusiness)
  {
    Intent intent = new Intent(cn.uc.gamesdk.b.e.b, SdkWebActivity_s.class);
    intent.putExtra("uiBusiness", uiBusiness);
    intent.putExtra("nativePageId", nativePageId);
    intent.putExtra("activity_work_type", 4);
    int flags = 805306368;
    intent.setFlags(flags);
    cn.uc.gamesdk.b.e.b.startActivity(intent);
  }

  public static void b()
  {
    Intent intent = new Intent(cn.uc.gamesdk.b.e.b, SdkWebActivity_s.class);
    intent.putExtra("uiBusiness", "pay");
    intent.putExtra("activity_work_type", 3);
    intent.setFlags(872415232);
    cn.uc.gamesdk.b.e.b.startActivity(intent);
  }
}