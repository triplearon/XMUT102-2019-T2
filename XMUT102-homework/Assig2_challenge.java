//作者:d2_dxy 1812409208
//代码冗杂 仅供参考
//首发时间:2019/9/22 0:46
//遵循Gpl开源协议 调用请注明原作者和出处 该项目由xmut_1812409208维护.





import ecs100.*;  //某带学专用库

class date{
        private int year;
        private int month;
        private int day;
        //basic
        
        private int Startday;                 //用来存取某年第一天是周几
        private int Dayofyear=0;              //用来存取某年某天在全年是第多少天
        private boolean Isdaytrue=false;      //用来存取判断日期方法返回的结果
        private boolean Isleapyear=false;     //用来存取判断瑞年返回的结果
        private int Ys;                       //求具体周几的余数
        public String DayOfWeek;              //存取具体某天是周几的字符串
        //实例变量部分
/* --------------------------  以下 对象方法 ----------------------------*/
 
 //Setter方法 用来传值给private的变量们。
public void SetDate(int x,int y,int z){
    year=x;
    month=y;
    day=z;
}

//判断给出的数据是否正确，是否符合日历的准则。
public void Istrue(){
   if(year<1||day<1||month<1||month>12){
       Isdaytrue=false;}
   if(year%400==0||(year%4==0&&year%100!=0)){
       Isleapyear=true;}

   if((month==1||month==3||month==5||month==7||month==8||month==10||month==12)&(day<32 & day>0)){
        Isdaytrue = true;}
   if((month==4||month==6||month==9||month==11)&(day<31 & day>0)){
        Isdaytrue = true;}

        if(Isleapyear&month ==2&(day<30&day>0)){
        Isdaytrue = true;
 
    }
        if(!Isleapyear&month==2&(day<29&day>0)){
        Isdaytrue = true;
    }
}
//以下全是getter类方法 用来调试和传值给主函数的变量
public boolean GetIsleap(){
    return Isleapyear; }       //返回是否闰年

public boolean GetDaytrue(){
    return Isdaytrue;}         //返回时期是否正确

public int GetStart(){
    return Startday;          //返回某年第一天具体周几
}

public int GetdayV(){
    return Dayofyear;         //返回某年某天在全年是第多少天
}
//getter类方法结束  
  
  //得到具体日在某年是第几天方法
public void dayV(){
    if(Isdaytrue){
    int x1=0;
    int[] Nyear={31,28,31,30,31,30,31,31,30,31,30,31};   //符合平年时启用的数组
    int[] Lyear={31,29,31,30,31,30,31,31,30,31,30,31};   //符合闰年时启用的数组
    //如果是闰年
    if(Isleapyear){
       while(x1<month-1){
           Dayofyear=Dayofyear+Lyear[x1];
           x1++;
       }
        Dayofyear=Dayofyear+day;            
    }
    //如果是平年
    if(!Isleapyear){
       while(x1<month-1){
            Dayofyear=Dayofyear+Nyear[x1];
            x1++;
       }
        Dayofyear=Dayofyear+day;    
    }
}


//核心部分：判断某年第一天是周几
/*思路：因为2000年第一天是周6 每逢闰年 第二年的第一天+2 2001是周一(8) 2002(9) 2003(10) 2004(11) 2004又是闰年 则2005(13) 则以2000的6为标准
差多少年就相当于+多少个6 遇上闰年又＋1，有多少个闰年就＋多少个闰年*/
}
    
public void FindStartDay(){
    
    if(Isdaytrue){ //在日期正确时才执行
    int Myear=year-2000;
    int fl=Myear/4;          //Four a leap 4年一闰
    int hnl=Myear/100;       //Hundred not a leap 100年不闰
    int fhl=Myear/400;       //Four hundred a leap 400年又闰
    int HowMuchleapyearWehave=fl-hnl+fhl+1; //求得从2000年到目标年中一共有多少个闰年
    int WeekGo=0;
    
    if(Isleapyear){
     WeekGo=6+Myear+HowMuchleapyearWehave-1;}  //因为闰年的下一年才会进1 如果输入的年是闰年 但闰年数量已经被加上 遂用-1平衡
    
    if(!Isleapyear){
     WeekGo=6+Myear+HowMuchleapyearWehave;}   //非闰年，正常加
      Startday=WeekGo%7;//得到某年初始日
}


// 通过某年第一天判断某年某天具体周几方法 以下全是
}

public void S1(){
    Ys=Dayofyear%7;//求得余数
    String WeekS1[]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};//用数组存值 感谢13提供的思路
    DayOfWeek=WeekS1[Ys];//从数组取值
}

public void S2(){
    Ys=Dayofyear%7;
    String WeekS2[]={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    DayOfWeek=WeekS2[Ys];
}

public void S3(){
    Ys=Dayofyear%7;
    String WeekS3[]={"Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday","Monday","Tuesday"};
    DayOfWeek=WeekS3[Ys];
}

public void S4(){
    Ys=Dayofyear%7;
    String WeekS4[]={"Wednesday","Thursday","Friday","Saturday","Sunday","Monday","Tuesday"};
    DayOfWeek=WeekS4[Ys];
}

public void S5(){
    Ys=Dayofyear%7;
    String WeekS5[]={"Thursday","Friday","Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday"};
    DayOfWeek=WeekS5[Ys];
}

public void S6(){
    Ys=Dayofyear%7;
    String WeekS6[]={"Friday","Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday"};
    DayOfWeek=WeekS6[Ys];
}

public void S7(){
    Ys=Dayofyear%7;
    String WeekS7[]={"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
    DayOfWeek=WeekS7[Ys];
}
//以上 通过第一天判断某一天方法结束
}//date类结束


//以下 main函数部分

public void Challenge(){//作业挑战题
     
     date a=new date();//实例化date对象 名a
      
      
      //设三个形参传值给a对象
      int x=UI.askInt("Year(>=2000): ");
      int y=UI.askInt("Month: ");
      int z=UI.askInt("Day: ");
      a.SetDate(x,y,z);//调用SetDate函数 传值 设定基本的年月日
      
      
      a.Istrue();//调用Istrue函数 判断a对象日期是否正确
      boolean IsRight=a.GetDaytrue();//返回a日期正确与否的值 赋给IsRight
      
      
      if(IsRight){//Isright对 则执行
        a.dayV();//获得a对象具体日是某年第某天
        a.FindStartDay();//获取a对象某年第一天是周几
        int Aday=a.GetStart();//返回Ys的值给Aday
        //判断块，写的太傻逼了 主要是我不会，大神绕道谢谢/
        if(Aday==1){
        a.S1();}
        if(Aday==2){
        a.S2();}
        if(Aday==3){
        a.S3();}
        if(Aday==4){
        a.S4();}
        if(Aday==5){
        a.S5();}
        if(Aday==6){
        a.S6();}
        if(Aday==0){
        a.S7();}
        
        //打印结果
        UI.println(x+"/"+y+"/"+z+" is a valid date");
        UI.println("And that day is "+a.DayOfWeek);
        }
        else{//反之 日期值不对 输出日期不对的语句
        UI.println(x+"/"+y+"/"+z+" is not a valid date");
        UI.println("You give a wrong date!!!");
        }//if 完
    }//主函数 完

/* 仅供参考 我只是刚学java的菜鸡 欢迎大神给出建议 3q谢谢 */

