public class MyFirstProgram {

   public static void main (String[] args) {
 /* Создаем объекты, которые являются представителями класса Point, тоесть эта функция принимает объект типа Point */
      Point p1 = new Point(10, 15);
      Point p2 = new Point(18, 12);



      System.out.println("Дистанция между точками р1 и р2 = " + distance(p1, p2));
      System.out.println("Дистанция между точками р1 и р2 посчитанная при помощи метода в классе Point = " + p2.distance(p1));
   }

   public static double distance (Point t1, Point t2){
      double r = (t2.x-t1.x)*(t2.x-t1.x)+(t1.y-t2.y)*(t1.y-t2.y);
      return Math.sqrt(r);
   }
} 