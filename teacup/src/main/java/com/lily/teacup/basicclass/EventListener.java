package com.lily.teacup.basicclass;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import java.lang.reflect.Method;


/**
 * 防抖事件
 * 用法setOnClickListener(new EventListener(obj));
 * obj类，必须得有clickMethod(View)方法。默认是onClick(View)方法。
 */
public class EventListener implements OnClickListener,
        OnLongClickListener, OnItemClickListener, OnItemSelectedListener,OnItemLongClickListener {

    private Object handler;

    // 点击事件的方法，默认是onClick
    private String clickMethod = "onClick";

    private String longClickMethod;
    private String itemClickMethod;
    private String itemSelectMethod;
    private String nothingSelectedMethod;
    private String itemLongClickMethod;

    // 间隔时间
    private final static int INTERVAL_TIME = 1000;

    public EventListener(Object handler) {
        this.handler = handler;
    }

    public EventListener click(String method){
        this.clickMethod = method;
        return this;
    }

    public EventListener longClick(String method){
        this.longClickMethod = method;
        return this;
    }

    public EventListener itemLongClick(String method){
        this.itemLongClickMethod = method;
        return this;
    }

    public EventListener itemClick(String method){
        this.itemClickMethod = method;
        return this;
    }

    public EventListener select(String method){
        this.itemSelectMethod = method;
        return this;
    }

    public EventListener noSelect(String method){
        this.nothingSelectedMethod = method;
        return this;
    }

    public boolean onLongClick(View v) {
        return invokeLongClickMethod(handler,longClickMethod,v);
    }

    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        return invokeItemLongClickMethod(handler, itemLongClickMethod,arg0,arg1,arg2,arg3);
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        invokeItemSelectMethod(handler,itemSelectMethod,arg0,arg1,arg2,arg3);
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        invokeNoSelectMethod(handler,nothingSelectedMethod,arg0);
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        invokeItemClickMethod(handler,itemClickMethod,arg0,arg1,arg2,arg3);
    }

    public void onClick(View v) {

        invokeClickMethod(handler, clickMethod, v);
    }

    //上一次点击时间
    static long lastclicktime=0;
    /**
     * 点击事件
     * @param handler
     * @param methodName
     * @param params
     * @return
     */
    private static Object invokeClickMethod(Object handler, String methodName, Object... params){
        if(handler == null)
            return null;
        Method method = null;
        try{

            if(System.currentTimeMillis()-lastclicktime > INTERVAL_TIME){
                method = handler.getClass()
                        .getDeclaredMethod(methodName,View.class);
                Object result = method.invoke(handler, params);
                lastclicktime = System.currentTimeMillis();
                return result;
            }
        }catch(Exception e1){
            handlerException(e1);
        }

        return null;
    }

    public static void handlerException(Exception e){

    }

    /**
     * 长按事件
     * @param handler
     * @param methodName
     * @param params
     * @return
     */
    private static boolean invokeLongClickMethod(Object handler, String methodName, Object... params){
        if(handler == null) return false;
        Method method = null;
        try{
            //public boolean onLongClick(View v)
            method = handler.getClass().getDeclaredMethod(methodName,View.class);
            if(method!=null){
                Object obj = method.invoke(handler, params);
                return obj==null?false: Boolean.valueOf(obj.toString());
            }else{
                method = handler.getClass().getDeclaredMethod(methodName);
                if(method!=null){
                    Object obj = method.invoke(handler);
                    return obj==null?false: Boolean.valueOf(obj.toString());
                }
                else
                    throw new RuntimeException("no such method:"+methodName);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;

    }



    private static Object invokeItemClickMethod(Object handler, String methodName, Object... params){
        if(handler == null) return null;
        Method method = null;
        try{

            method = handler.getClass().getDeclaredMethod(methodName,AdapterView.class,View.class,int.class,long.class);
            if(method!=null){
                if(System.currentTimeMillis()-lastclicktime > INTERVAL_TIME){
                    Object result= method.invoke(handler, params);
                    lastclicktime= System.currentTimeMillis();
                    return result;
                }
            }else{
                method = handler.getClass().getDeclaredMethod(methodName,int.class,long.class);
                if(method!=null){
                    Object result=  method.invoke(handler, params[2],params[3]);
                    lastclicktime= System.currentTimeMillis();
                    return result;
                }else
                    throw new RuntimeException("no such method:"+methodName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


    private static boolean invokeItemLongClickMethod(Object handler, String methodName, Object... params){
        if(handler == null) throw new RuntimeException("invokeItemLongClickMethod: handler is null :");
        Method method = null;
        try{
            ///onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
            method = handler.getClass().getDeclaredMethod(methodName,AdapterView.class,View.class,int.class,long.class);
            if(method!=null){
                Object obj = method.invoke(handler, params);
                return Boolean.valueOf(obj==null?false: Boolean.valueOf(obj.toString()));
            }
            else{
                method = handler.getClass().getDeclaredMethod(methodName, int.class,long.class);
                if(method!=null){
                    Object obj = method.invoke(handler,params[2],params[3]);
                    return Boolean.valueOf(obj==null?false: Boolean.valueOf(obj.toString()));
                }else

                    throw new RuntimeException("no such method:"+methodName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }


    private static Object invokeItemSelectMethod(Object handler, String methodName, Object... params){
        if(handler == null) return null;
        Method method = null;
        try{
            ///onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3)
            method = handler.getClass().getDeclaredMethod(methodName,AdapterView.class,View.class,int.class,long.class);
            if(method!=null)
                return method.invoke(handler, params);
            else
                throw new RuntimeException("no such method:"+methodName);
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private static Object invokeNoSelectMethod(Object handler, String methodName, Object... params){
        if(handler == null) return null;
        Method method = null;
        try{
            //onNothingSelected(AdapterView<?> arg0)
            method = handler.getClass().getDeclaredMethod(methodName,AdapterView.class);
            if(method!=null)
                return method.invoke(handler, params);
            else
                throw new RuntimeException("no such method:"+methodName);
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }



}

