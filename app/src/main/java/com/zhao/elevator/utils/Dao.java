package com.zhao.elevator.utils;

import android.util.Log;

import com.zhao.elevator.entity.Elevator;

/**
 * Created by zhao on 2016/12/8.
 */

public class Dao {

    /**
     * AT电梯以额定速率匀速行驶途径一层所用的时间   假设4秒
     * OT电梯加减速、开关门和停靠的平均时间        假设15秒
     * X1电梯在本方向（当前运行方向）到达的最远楼层
     * X2电梯在本方向（当前运行方向）后方的最远楼层
     * C1用户所在楼层--->userLocation
     * C2电梯在外召唤来时的当前楼层--->position
     */
    public static final double AT=4.0d;
    public static final double OT=15.0d;

    //计算电梯到达用户所在楼层需要的时间
    //public double calTime(int userLocation,String userAction)
    public static double calTime(Elevator elevator, int userLocation, String userAction)
    {
        double spendTime=0.0;
        int X1=0;
        int X2=0;
        int C1=userLocation;
        int C2= Integer.parseInt(elevator.getPosition());
        int N=elevator.getDockFloor().length;
        //	int X1=
        //判断用户在楼梯上还是下
        int elevatorLocation= Integer.parseInt(elevator.getPosition());
        if(elevator.getAction().equals("stop"))
        {
            Log.i("funTest", "电梯处于" + elevator.getAction() + "状态");
            Log.i("funTest", "用户想要" + userAction);
            spendTime= Math.abs(C1-C2)*AT+N*OT;
            Log.i("funTest","T=TR+TS=|C1-C2|*AT+N*OT---->方法1 用户需要等待的时间为"+spendTime+"秒");
        }
        else
        {
            if(elevator.getAction().equals(userAction))//召唤信号的方向与电梯当前运行方向同向
            {   //在电梯运行方向前方
                if((userAction.equals("up")&&userLocation>elevatorLocation)||(userAction.equals("down")&&userLocation<elevatorLocation))
                {
                    //T=TR+TS=|C1-C2|*AT+N*OT（！！一！！）
                    Log.i("funTest0", "电梯处于"+elevator.getAction()+"状态");
                    Log.i("funTest", "用户想要" + userAction);

                    spendTime= Math.abs(C1-C2)*AT+N*OT;
                    Log.i("funTest","T=TR+TS=|C1-C2|*AT+N*OT---->方法1 用户需要等待的时间为"+spendTime+"秒");

                }else if((userAction.equals("up")&&userLocation<elevatorLocation)||(userAction.equals("down")&&userLocation>elevatorLocation))
                {
                    //在电梯运行方向后方（！！三！！）
                    //T=(|X1-C2|+|X1-X2|+|C1-X2|)*AT+N*OT
                    Log.i("funTest", "电梯处于"+elevator.getAction()+"状态");
                    Log.i("funTest", "用户想要" + userAction);
                    Log.i("funTest","T=(|X1-C2|+|X1-X2|+|C1-X2|)*AT+N*OT");
                    if(userAction.equals("up")&&elevator.getAction().equals("up"))
                    {
                        X1= Integer.parseInt(elevator.getDockFloor()[elevator.getDockFloor().length-1]);
                        X2= Integer.parseInt(elevator.getDockFloor()[0]);
                    }
                    else
                    {
                        if(userAction.equals("down")&&elevator.getAction().equals("down"))
                        {
                            X1= Integer.parseInt(elevator.getDockFloor()[0]);
                            X2= Integer.parseInt(elevator.getDockFloor()[elevator.getDockFloor().length-1]);
                        }
                    }
                    spendTime=(Math.abs(X1-C2)+ Math.abs(X1-X2)+ Math.abs(C1-C2))*AT+N*OT;
                    Log.i("funTest","T=(|X1-C2|+|X1-X2|+|C1-X2|)*AT+N*OT---->方法3 用户需要等待的时间为"+spendTime+"秒");

                }
                //召唤信号的方向与电梯当前运行方向反向
            }else
            {
                //在电梯运行方向前方
                if ((userAction.equals("up") && userLocation > elevatorLocation) || (userAction.equals("down") && userLocation < elevatorLocation))
                {
                    //T=(|X1-C2|+|X1-C1|)*AT+N*OT（！！二！！）
                    if(userAction.equals("up")&&elevator.getAction().equals("down"))
                    {
                        X1= Integer.parseInt(elevator.getDockFloor()[0]);
                    }else
                    {
                        if(userAction.equals("down")&&elevator.getAction().equals("up"))
                        {
                            X1= Integer.parseInt(elevator.getDockFloor()[elevator.getDockFloor().length-1]);
                        }
                    }
                    Log.i("funTest", "电梯处于" + elevator.getAction() + "状态");
                    Log.i("funTest", "用户想要" + userAction);
                    Log.i("funTest", "T=(|X1-C2|+|X1-C1|)*AT+N*OT");
                    spendTime=(Math.abs(X1-C2)+ Math.abs(X1-C1))*AT+N*OT;
                    Log.i("funTest","T=(|X1-C2|+|X1-C1|)*AT+N*OT---->方法2 用户需要等待的时间为"+spendTime+"秒");
                }else
                {
                    //在电梯运行方向后方
                    if((userAction.equals("up")&&userLocation<elevatorLocation)||(userAction.equals("down")&&userLocation>elevatorLocation))
                    {
                        //T=(|X1-C2|+|X1-C1|)*AT+N*OT（！！二！！）
                        Log.i("funTest", "电梯处于"+elevator.getAction()+"状态");
                        Log.i("funTest", "用户想要" + userAction);
                        Log.i("funTest", "T=(|X1-C2|+|X1-C1|)*AT+N*OT");
                        spendTime=(Math.abs(X1-C2)+ Math.abs(X1-C1))*AT+N*OT;
                        Log.i("funTest","T=(|X1-C2|+|X1-C1|)*AT+N*OT---->方法2 用户需要等待的时间为"+spendTime+"秒");

                    }
                }
            }
        }
        return spendTime;
    }
}
