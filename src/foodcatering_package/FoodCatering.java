/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodcatering_package;
import static foodcatering_package.FoodCatering.mycus;
import java.util.Scanner; 
/**Our program main class
 * references stack overflow 
 *  lecture 10 , 4, 3 , 2, 7
 * java point
 *  W3 Schools 
 */
public class FoodCatering {

       static customer mycus [] = new customer[100];
       static int size = 3 ; 
       static int myindex  = -1;
       static fooditem menu [] = new fooditem [9];
       static String reports []= new String [16];
       static int reportsize =1 ;
       /**method to check exception of choices of users 
         he has just 5 choices
         @throws nvalidDataException*/
     static void checkexception (int mynumber)throws InvalidDataException
     { 
        
        if (mynumber >5 || mynumber<1)
          throw new InvalidDataException(" the number you enterd not exist please try again");
     }
     /**dynamic method for creating meal to our customer*/
     static int createmeal()
     {
              Scanner sc = new Scanner(System.in);
              if (mycus[myindex].category =='l')
                  System.out.print("you have two appetizers and a drink for free for your loyality you can chose them 3> ");
                System.out.println("our menu : ");
                int numberofitems = 0;
                while(true)
                {
                   for (int i=0 ; i<9 ; i++)
                    {
                      System.out.print(i+1);
                      System.out.print(" - ");
                      menu[i].displayinfo();
                    } 

                    try
                    {
                      System.out.println("enter the  number of the item you want :");
                      int itemnum = sc.nextInt();
                      itemnum--;  
                      System.out.println("how many instance you want from " +menu[itemnum].name);
                      int instances = sc.nextInt();
                      int j =0 ;
                      while(j<instances)
                          {
                            mycus[myindex].favorite_meal[mycus[myindex].getNum_of_favorite_meals()][numberofitems]= menu[itemnum];
                            j++; 
                            numberofitems++;
                          }
                      }
                     catch( ArrayIndexOutOfBoundsException e)
                      {
                        System.out.println(" the number of item is not found please try again ");
                      }
                      int numberofit =  numberofitems ;
                    System.out.print("if you want to add another food item press y , if you finish press f");
                    char choise = sc.next().charAt(0);
                    if (choise == 'f')
                    {
                      System.out.println("your meal consist of ");

                      numberofitems-- ;
                      int j =1 ;
                      while(numberofitems>=0)
                      {

                       System.out.println(j+" - "+ mycus[myindex].favorite_meal[mycus[myindex].getNum_of_favorite_meals()][numberofitems].name);
                       j++;
                       numberofitems --;
                      }
                      mycus[myindex].setNum_of_favorite_meals(mycus[myindex].getNum_of_favorite_meals()+1);
                    
                    System.out.print("Done :)");
                    return numberofit ;
                  }
                }   
              }
     /** dynamic method for creating order for customers in run time*/
     public static void creatorder()
     {   
        order.numoforders++;
        Scanner sc = new Scanner(System.in);
        order neworder ;
        double today = Math.random()*7;
        double month = Math.random()*12;
        if ((int)today ==0 )
           today = 1 ;
         if ((int)month ==0 )
            month= 1 ;
        date orderdate  = new date((int)today,(int)month);
        System.out.println("when do you want to receive your order");
        System.out.print(" deleviry day : ");
        int dday = sc.nextInt();
        System.out.print(" deleviry mounth : ");
        int dmounth = sc.nextInt();
        date delelvirydate  = new date(dday , dmounth);
        //System.out.print(mycus[myindex].getOrdersnumber());
        //polymorfithim
        if (mycus[myindex].category()=='l')
             neworder = new loyalty_order(mycus[myindex] ,orderdate ,delelvirydate);
        else 
            neworder = new guest_order(mycus[myindex] ,orderdate ,delelvirydate);
        System.out.println("chose your meal please");
        mycus[myindex].len = createmeal();
        int sizee = mycus[myindex].len/(mycus[myindex].getNum_of_favorite_meals());
        neworder.meal = mycus[myindex].favorite_meal[mycus[myindex].getNum_of_favorite_meals()-1];
        System.out.println(" you have net ptice "+Float.toString(neworder.netprice(sizee)));
        System.out.println(" you have discount "+Float.toString(neworder.discount()));
        System.out.println(" the total price is " + Float.toString(neworder.totalprice(neworder.discount() ,neworder.netprice(sizee) )));
        neworder.displayinfo();
        mycus[myindex].Orders[mycus[myindex].getOrdersnumber()]=neworder ;
        mycus[myindex].setOrdersnumber(mycus[myindex].getOrdersnumber()+1);
        System.out.println("thank you :) your order have been recorded");  
        mycus[myindex].category();
     }
     //override
    /**method for creating order for hard code customers 
     * @param cus customer that creating the order 
     */
    public static void creatorder(customer cus)
    {
      order.numoforders++;
      double today = Math.random()*7;
      double month = Math.random()*12;
       if ((int)today ==0 )
           today = 1 ;
         if ((int)month ==0 )
            month= 1 ;
      date orderdate  = new date((int)today,(int)month);
      double dday = Math.random()*7;
      double dmonth = Math.random()*12;
        if ((int)dday ==0 )
           dday = 1 ;
         if ((int)dmonth ==0 )
            dmonth= 1 ;
      date delvdate  = new date((int)dday,(int)dmonth);
      if (cus.category == 'l')
        cus.Orders[cus.getOrdersnumber()] = new loyalty_order(cus , orderdate , delvdate);
      else 
        cus.Orders[cus.getOrdersnumber()]= new guest_order(cus,orderdate , delvdate);
       double firstitem = Math.random()*8;
       double secitem = Math.random()*8;
       cus.Orders[cus.getOrdersnumber()].meal[0]=menu[(int)firstitem];
       cus.Orders[cus.getOrdersnumber()].meal[1]=menu[(int)secitem];
       cus.len =2 ;
       cus.setOrdersnumber(cus.getOrdersnumber()+1);
       cus.category();
    }
    public static void main(String[] args)throws InvalidDataException
    { 

       //different food items with differen categroies 
       menu[0] = new fooditem("chiken","main dish",12 ,4 , 50);
       menu[1] = new fooditem("meat","main dish",11 ,6 , 75 );
       menu[2] = new fooditem("fish","main dish",6 ,2 , 45 );
       menu[3] = new fooditem("pickles ","appetizers",2 ,10,15 );
       menu[4] = new fooditem("French fries ","appetizers",30 ,6, 20 );
       menu[5] = new fooditem("salad ","appetizers",4 ,8 , 25 );
       menu[6] = new fooditem("orange juice","drink",0 ,1 , 10 );
       menu[7] = new fooditem("ice tea","drink",5 ,1 , 5 );
       menu[8] = new fooditem("pepsi","drink",20 ,1 ,3);
       
       // customers
       mycus[0]= new customer("Maria","058" ,"misrelgdeda");
       mycus[1]= new customer("Mahmoud","059" ,"el 3absyaa");
       mycus[2]= new customer("Adham","0510" ,"el ma3ady");
       mycus[3]= new customer("Alaa","0511" ,"nasir city");
       mycus[0].category= 'l';
       mycus[1].category ='l';
       mycus[2].category ='g';
       mycus[3].category ='g';
       for (int i=0 ; i<2 ; i++)
       {
        creatorder(mycus[i]);
        creatorder(mycus[i]);
       }
       for (int i=2 ; i<4; i++)
         creatorder(mycus[i]);
       reports[0]="this project was very hard but it also very intersting thanks :)";
       Scanner sc = new Scanner(System.in);
       while(true)
       {
        try
       {
            System.out.println("Hello : Are you user or Admine ");
            System.out.print("Admine press (1) - user press (2)");
            int mode = sc.nextInt();
            if (mode == 1)
            {
               System.out.println("HELLO POSS :)  here is the last update of the place information ");
               System.out.println( " nomber of customers is ---> "+ customer.getNumofcustomers());
               System.out.println( " nomber of orders is ---> "+ order.numoforders);
               System.out.println( " rate of profite is ---> "+ (int)order.rate_of_profit()+ "%");
               System.out.println( " if you want to know spsific info about customer press 1" );
               System.out.println( " if you want to know the report issues press 2 " );
               System.out.println( " if you want to exit press 3" );
               int sample = sc.nextInt();
               if (sample == 1)
               {
                 System.out.print("please enter the customer name - > ");
                 String name = sc.next(); 
                   boolean found = false ;
                   for(int i=0 ; i<=size ; i++)
                  { 
                    if ( mycus[i].name.equals(name))
                   {
                        found = true ; 
                        myindex = i ;
                        System.out.println("customer phone number is ---> " + mycus[i].getPhonenum());
                        System.out.println("customer address is --->" + mycus[i].getAddress());
                        System.out.println("his orders info : ");
                        System.out.println("Number of orders he made "+mycus[i].getOrdersnumber());
                        for(int j=0 ; j<mycus[i].getOrdersnumber(); j++)
                        {
                        System.out.print("1# ");
                        mycus[i].Orders[ j].displayinfo();
                        }
                    }
                   }
                   if (!found)
                       System.out.println("this customer name is not exist");
               }
               else if (sample == 2)
               {
                  for (int i=0 ; i<reportsize ; i++)
                      System.out.println(reports[i]);
                  System.out.println("We give promesses that we will solve those problems :) ");
                  System.out.println("you can do it OUR POSS :) 3> ");
               }
           
        }
        else
            {
            System.out.print("hello : please enter your name ");

            String c = sc.next();

            boolean found = false ;
            for(int i=0 ; i<=size ; i++)
            {
              if ( mycus[i].name.equals(c))
              {
               found = true ; 
               myindex = i ;
               System.out.println("welcome 3> your phone number is ---> " + mycus[i].getPhonenum());
               System.out.println("your address is --->" + mycus[i].getAddress());
              }
            }
            if (!found)
            {
               size++ ;
               myindex = size;
               System.out.println("you are new customer thank you for chosing us welcome 3> 3> please enter your data");
               System.out.print("phone number:");
               String ph = sc.next();
               System.out.print("Address :");
               String ads = sc.next();
               mycus[myindex]= new customer(c,ph,ads);
            }
            
                System.out.println("enter the  number of the option you want : ");
                System.out.println("1-Create New Order");
                System.out.println("2-Upload your Order");
                System.out.println("3-Cancel your Order");
                System.out.println("4-Add meal to yor wish list");
                System.out.println("5-Report quality issues or a review.");
                int mycho = sc.nextInt();
                checkexception(mycho);

                if (mycho == 1)
                {
                 creatorder();
                }

                else if (mycho == 4)
                {
                 createmeal();
                }

                else if (mycho == 2)
                {
                    if ( mycus[myindex].getOrdersnumber()==0){
                     System.out.println("sorry !! you do not have orders to upload yet but you can create new order : )");
                     System.out.println("you are now creating new order");
                     creatorder();
                    }
                    else 
                    {
                    System.out.println("to add extra item in meal press (1)");
                    System.out.println("to extend delivery date press (2)");
                    int cho = sc.nextInt();
                    order myorder = mycus[myindex].Orders[mycus[myindex].getOrdersnumber()-1];
                    if (cho == 1)
                    {
                        int numberofitems = mycus[myindex].len;

                        while(true)
                        {
                         for (int i=0 ; i<9 ; i++)
                          {
                            System.out.print(i+1);
                            System.out.print(" - ");
                            menu[i].displayinfo();
                          } 

                         try
                         {
                           System.out.println("enter the  number of the item you want :");
                           int itemnum = sc.nextInt();
                           itemnum--;  
                           System.out.println("how many instance you want from " +menu[itemnum].name);
                           int instances = sc.nextInt();
                           int j =0 ;
                           while(j<instances)
                             {
                                 myorder.meal[numberofitems]=menu[itemnum];
                                 j++; 
                                 numberofitems++;
                             }
                           }
                          catch( ArrayIndexOutOfBoundsException e)
                           {
                             System.out.println(" the number of item is not found please try again ");
                           }
                           System.out.print("if you want to add another food item press y , if you finish press f");
                           char choise = sc.next().charAt(0);
                           if (choise == 'f')
                           {
                            System.out.println("your meal consist of ");

                            mycus[myindex].favorite_meal[mycus[myindex].getNum_of_favorite_meals()]= myorder.meal;
                            mycus[myindex].Orders[mycus[myindex].getOrdersnumber()]= myorder ;
                            numberofitems-- ;
                           //  System.out.print( myorder.meal[1].name + " ");
                            int j =1 ;
                            while(numberofitems>=0)
                             {
                              System.out.println( j + " - "+myorder.meal[numberofitems].name + " ");
                              numberofitems --;
                              j++;
                             }
                            System.out.print("Done :)");
                            break ;
                           }
                        }
                    }
                    else 
                    {
                        System.out.println("when do you want to receive your order");
                        System.out.print(" deleviry day : ");
                        int newday = sc.nextInt();
                        System.out.print(" deleviry mounth : ");
                        int newmonth = sc.nextInt();
                        date newdate = new date (newday , newmonth);
                        myorder.delivery_date = newdate ;
                    }
                    mycus[myindex].Orders[mycus[myindex].getOrdersnumber()] = myorder ;
                  }
                }
                  else if (mycho == 3)
                  {
                    order.numoforders--;
                    date datenow = new date (4 ,6);
                    if (mycus[myindex].Orders[mycus[myindex].getOrdersnumber()-1].comp(datenow) != true)
                    {
                       mycus[myindex].Orders[mycus[myindex].getOrdersnumber()]= null ;
                       mycus[myindex].setOrdersnumber(mycus[myindex].getOrdersnumber()-1);
                       mycus[myindex].setNum_of_favorite_meals(mycus[myindex].getNum_of_favorite_meals()-1);
                       order.numoforders--;
                       System.out.println("your last order calnceld");
                    }
                    else 
                       System.out.print("you can not cancel the order started to prepair"); 
                   }
                  else if (mycho == 5 )
                  {
                     System.out.print("Our dear customer please enter your report");
                     String report = sc.next();
                     reports[reportsize]= report ; 
                     reportsize++;
                     System.out.print("We promess you to solve the problem soon thank you :)");
                  }
                }
       }
               // Java defined exception
                catch(InvalidDataException e)
                {
                   System.out.println( e.getMessage());
                }
                catch(Exception anye)
                {
                    System.out.println("some thing went wrong please");
                }
               System.out.println("if you want to continue press y or press n to exit");
                char choise = sc.next().charAt(0);
                if (choise == 'n')
                     break ;
            }    
          
       }
    }
 /** class date represent the date ( day and month)*/   
class date {
  int day ;
  int month;
  /** Contractor to place date with 
  * @param d for day
  * @param m for month
  */
 public date (int d , int m )
  {
    this.day = d ;
    this.month= m ;
  }
}
/**interface for creating unique code ability */
 interface codecreator
{
  /** helper is constant for calculations*/
  public static final int helper  = 5;
  //interface segregation principle
  public String uniquecode ();

}
/** represents the customer data and functions */
 class customer 
{  
    //Different Access modifiers
    String name ; 
    private String phonenum ;
    private String address ;
    static int numofcustomers =0 ; //static data member
    private int ordersnumber ;
    private int num_of_favorite_meals ;
    fooditem favorite_meal [][] = new fooditem[20][1500];
    order Orders []= new order [1500];
    public char category ;
    public int len ; //lenght of last order 
    // setters and getters encapsolition 
    /**getter of number of favorite meals */
    public int getNum_of_favorite_meals() {
        return num_of_favorite_meals;
    }
  /**setter of number of favorite meals*/
    public void setNum_of_favorite_meals(int num_of_favorite_meals) {
        this.num_of_favorite_meals = num_of_favorite_meals;
    }
      /**getter of customer phone number*/
    public String getPhonenum() {
        return phonenum;
    }
    /**getter of customer Address*/
    public String getAddress() {
        return address;
    }
 /**setter of customer Address*/
    public void setAddress(String address) {
        this.address = address;
    }
 /**setter of customer orders number */
    public void setOrdersnumber(int ordersnumber) {
        this.ordersnumber = ordersnumber;
    }
 /**getter of  number of customers */
    public static int getNumofcustomers() {
        return numofcustomers;
    }
 /**getter of customer orders number */
    public int getOrdersnumber() {
        return ordersnumber;
    } 
     /**constructor that takes values of customer data*/
   public customer (String name , String phonenum , String address)
   {
     numofcustomers++;
     this.name = name ;
     this.address = address ;
     this.phonenum = phonenum ;
     num_of_favorite_meals =0;
     ordersnumber =0 ;
     this.len =-1;
   }/**function to calculate and get the category of customer 
    *l means he is loyalty customer
    *g means he is guest 
    */
   public char category()
   {
      char categ ;
      if (this.ordersnumber > 1)
       this.category = 'l';
      else 
       this.category = 'g';
      return this.category ;
   }
}
/**class represent item of food 
 * and it can has a unique code also 
 */
 class fooditem implements codecreator
{
  public String name ;
  private final String catigory ;//final data member 
  private final float calories ;
  private int portionnum ;
  private float price ;
  /**constructor of food item to set its data */
  public fooditem(String name , String catigory , float calories , int portionnum , float price )
  {
     this.name = name ;
     this.catigory = catigory ;
     this.portionnum = portionnum ;
     this.calories = calories ;
     this.price = price ;
  }
/**getter of price
 *@return price of item 
 */
    public float getPrice() {
        return price;
    }
   /**display information of the food item (name - category - calories - price )*/
  public void displayinfo()
  {
    System.out.println(this.name );
    System.out.println( "catigory is "+ this.getCatigory() +" " +"number of calories =  "+Float.toString(this.getCalories())+" "+"number of portion  =  "+Float.toString(this.getPortionnum())+" price = " +Float.toString(price)+"Eg");
  
  }
  /**getter category of food item
   *@return category of food item*/
    public String getCatigory() {
        return catigory;
    }
 /**getter number of calories of food item
  *@return number of calories of food item
  */
    public float getCalories() {
        return calories;
    }
/**getter number of persons food item can fit
 *@return number of persons food item can fit
 */
    public int getPortionnum() {
        return portionnum;
    }
  /**method to create unique code for food item 
   *@return unique code */
  @Override
    public String uniquecode ()
    {
         String unicode = Float.toString(calories + portionnum*helper);
         unicode+=this.name;
         return unicode ;
    }
}
/**abstract class represent the order and its information */
 abstract class order implements codecreator // abstract class 
{   
  fooditem meal []= new fooditem[1500];
  String uniquecode ;
  date order_date ;
  date delivery_date ;
  float totalprice ;
  static int numoforders ;
  static int total_profit =0 ;
  customer cus ;
  /**order constructor to set data or order*/
 public order(customer cus , date Odate ,date Ddate )
  {
     this.cus = cus ;
     this.delivery_date = Ddate;
     this.order_date = Odate;
  
  }
  //static method
  /**static method for calculating the rate of profit
   *@return the rate and if it less than 100% that means we are losing money */
  public static float rate_of_profit()//static method
  {
    return (Math.abs(customer.numofcustomers - numoforders)*10/numoforders)*100;
  }
  /** it displays the info of order (customer who ordered (name - address - phone number - category  )- delivery date - order date )_ )*/
  public void displayinfo()
  {
   System.out.println(cus.name + " "+ cus.getAddress()+" "+cus.getPhonenum()+ " "+cus.category());
   System.out.print("delivery date is : ");
   System.out.print("deleviry date : "+this.delivery_date.day +" /" );
   System.out.print(this.delivery_date.month+ " /");
   System.out.print("order date : " +this.order_date.day +" /" );
   System.out.print(this.order_date.month); 
   //Calculated data members
   this.uniquecode= uniquecode();
   System.out.println("unique code is ---> "+ this.uniquecode);
  }
  // final method
  /** final method for calculating the price after applying the discount*/
   public final float totalprice (float discount , float totalprice)//final method
  {
    if (totalprice<discount)
    {
        System.out.println("congratulations your order is for free");
        return 0 ;
    }
    // calculated data member 
    this.totalprice = Math.abs(totalprice - discount) ;
    total_profit += this.totalprice ;
    return this.totalprice ;
  }
   /**method to create unique code for order
   *@return unique code */
    @Override
    public String uniquecode ()
    {
        String unicode = Integer.toString(helper* this.delivery_date.day+this.order_date.month);
        System.out.println(unicode);
        unicode+=cus.name ;
        return unicode ;
    } 
    /**this method for comparison between two dates 
     *@return true if two dates are equal and false otherwise 
     */
    public boolean comp(date d)
    {
      if (this.delivery_date.day == d.day && this.delivery_date.month == d.month)
          return true ;
      else 
         return false ;
    
    }
    //abstract method 
    /**abstract method to calculate the discount */
    public abstract float discount();
     //abstract method 
  /**abstract method for calculating the price of the order before apply the discount */
   public abstract float netprice(int size);
}
/**this class is special for orders for loyalty customers and it inherited from abstract order class*/
  class loyalty_order extends order //inharitance
{
  int loyality_factor ;
  /** constructor to set values of loyalty customers order and it also calls order constructor to set its values*/
  public loyalty_order(customer cus ,date Odate ,date Ddate  )
  {
       super(cus, Odate , Ddate );
       this.loyality_factor = cus.getOrdersnumber();
  }
  //overriding 
  /**override this method to calculate the discount for loyalty customers only*/
  public float discount()
  {
       return (this.loyality_factor*10)/2 ;
  }
  // overriding
   /**override this method to calculate the net price for loyalty customers only
    * @return the net price after applying loyalty rules 
    */
   public float netprice(int size)
  {
      float sum =0 ;
      int app =2 , drink =1;
      for (int i =0 ; i< size ; i++)
      {
        if (meal[i].getCatigory().equals("appetizers" )&& app>0)
        {
           app--;
        }
        else if (meal[i].getCatigory().equals("drink" )&& drink>0)
        {
            drink--;
        }
        else 
        {   
           sum+=meal[i].getPrice();
        }
       }
       return sum ;
  }
}
 /**this class is special for orders for guest customers and it inherited from abstract order class */
 class guest_order extends order//inharitance
  {
    int guset_geft ;
     /** constructor to set values of guest customers order and it also calls order constructor to set its values*/
    public guest_order(customer cus , date Odate ,date Ddate )
    {
         super(cus,Odate , Ddate );
         this.guset_geft = Math.abs(cus.getOrdersnumber()-cus.getNum_of_favorite_meals());
    }
     //overriding 
    /**override this method to calculate the discount for guest customers only*/
    public float discount()
    {
         return (this.guset_geft*10)/4 ;
    }
      //overriding 
     /**override this method to calculate the net price for guest customers only
    * @return the net price 
    */
    public float netprice(int size)
   {
     float sum =0 ;
      for (int i =0 ; i< size ; i++)
      {
       sum+=meal[i].getPrice();
      }
     return sum ;
    }
   }
/** class inherited from exception class and it handles the invalid inputs */
 class InvalidDataException extends Exception //user defined exception
 {
    InvalidDataException(String msg)
    {
       super(msg);
    }
 }