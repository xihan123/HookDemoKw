package com.xihan.hookdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;



public class Hook implements IXposedHookLoadPackage {

    public static Activity 上下文;
    public static final String hookPackageName = "cn.kuwo.player";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        //判断是不是这个软件包名,不判断的话，全部的软件都会被hook
        if (lpparam.packageName.equals(hookPackageName))

            if(上下文==null){XposedHelpers.findAndHookMethod(Activity.class,"onCreate",Bundle.class,new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam 参数) throws Throwable {
                    super.afterHookedMethod(参数);
                    if (上下文 == null) {
                        上下文 = (Activity) 参数.thisObject;
                        Toast.makeText(上下文, "模块已开启", Toast.LENGTH_LONG).show();
                    }
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        XposedHelpers.findAndHookMethod("android.support.multidex.MultiDexApplication", lpparam.classLoader, "attachBaseContext", Context.class, new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                doHook(lpparam);
                            }
                        });
                    } else
                        doHook(lpparam);


                }

                private void doHook(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

                    //暂未zd  4
                    XposedHelpers.findAndHookMethod("cn.kuwo.peculiar.c.d", lpparam.classLoader, "c", XC_MethodReplacement.returnConstant(1));
                    XposedHelpers.findAndHookMethod("cn.kuwo.peculiar.a.a", lpparam.classLoader, "b", XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.peculiar.c.c", lpparam.classLoader, "c", XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.peculiar.c.c", lpparam.classLoader, "a", XC_MethodReplacement.returnConstant(true));

                    //豪华类型 7
                    XposedHelpers.findAndHookMethod("cn.kuwo.mod.comment.bean.CommentInfo", lpparam.classLoader, "getVipLuxuryType", XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.mod.detail.musician.moments.model.MomentsUser", lpparam.classLoader, "getVipLuxuryType", XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.sing.bean.KSingFollowFan", lpparam.classLoader, "getVipLuxuryType", XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.mod.comment.bean.CommentInfo", lpparam.classLoader, "getState", XC_MethodReplacement.returnConstant(1));
                    XposedHelpers.findAndHookMethod("cn.kuwo.mod.detail.musician.moments.model.MomentsUser", lpparam.classLoader, "getYearType", XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.mod.comment.bean.CommentInfo", lpparam.classLoader, "getYearType", XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.sing.bean.KSingFollowFan", lpparam.classLoader, "getYearType", XC_MethodReplacement.returnConstant(true));

                    //下载 9
                    Class<?> threeClass = XposedHelpers.findClass("cn.kuwo.service.DownloadProxy$Quality",lpparam.classLoader);
                    XposedHelpers.findAndHookMethod("cn.kuwo.base.bean.Music", lpparam.classLoader, "isDownloadFree", threeClass,XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.base.bean.Music", lpparam.classLoader, "isDownloadFree", XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.base.bean.Music", lpparam.classLoader, "isDownHighQualityFree", XC_MethodReplacement.returnConstant(true));

                    XposedHelpers.findAndHookMethod("cn.kuwo.base.bean.Music", lpparam.classLoader, "isDownloadVip", XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.base.bean.Music", lpparam.classLoader, "isOverseasPlayFree", XC_MethodReplacement.returnConstant(true));
                    Class<?> twoClass = XposedHelpers.findClass("cn.kuwo.base.bean.MusicQuality",lpparam.classLoader);
                    XposedHelpers.findAndHookMethod("cn.kuwo.base.bean.Music", lpparam.classLoader, "isOverseasPlayFree", twoClass,XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.base.bean.Music", lpparam.classLoader, "isListenVip", XC_MethodReplacement.returnConstant(true));
                    Class<?> oneClass = XposedHelpers.findClass("cn.kuwo.base.bean.MusicQuality",lpparam.classLoader);
                    XposedHelpers.findAndHookMethod("cn.kuwo.base.bean.Music", lpparam.classLoader, "isPlayFree",oneClass, XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.base.uilib.KwJavaScriptInterface", lpparam.classLoader, "isVipPayFinished", XC_MethodReplacement.returnConstant(true));

                    //Vip购买 3
                    XposedHelpers.findAndHookMethod("cn.kuwo.base.uilib.KwJavaScriptInterface", lpparam.classLoader, "isVipPayFinished", XC_MethodReplacement.returnConstant(true));
                    XposedHelpers.findAndHookMethod("cn.kuwo.mod.search.DigitAlbum", lpparam.classLoader, "getPayType", XC_MethodReplacement.returnConstant(1));
                    XposedHelpers.findAndHookMethod("cn.kuwo.mod.search.DigitAlbum", lpparam.classLoader, "isHadBought", XC_MethodReplacement.returnConstant(true));

                    //广告 3
                    XposedHelpers.findAndHookMethod("cn.kuwo.mod.mobilead.audioad.AudioAdInfo", lpparam.classLoader, "isShowAdMark", XC_MethodReplacement.returnConstant(false));
                    XposedHelpers.findAndHookMethod("cn.kuwo.mod.mobilead.messad.MessAdModel", lpparam.classLoader, "isAdAvailable", XC_MethodReplacement.returnConstant(false));
                    XposedHelpers.findAndHookMethod("cn.kuwo.mod.mobilead.KuwoAdUrl$AdUrlDef", lpparam.classLoader, "getUrl", String.class,XC_MethodReplacement.returnConstant(""));

                    //个性换肤 1
                    Class<?> personClass = XposedHelpers.findClass("cn.kuwo.mod.theme.bean.star.StarTheme",lpparam.classLoader);
                    XposedHelpers.findAndHookMethod("cn.kuwo.mod.theme.detail.star.StarThemeDetailPresenter", lpparam.classLoader, "checkStarThemeFree", personClass,XC_MethodReplacement.returnConstant(true));


                }



            });}}
}
