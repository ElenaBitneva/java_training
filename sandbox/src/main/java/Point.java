/**
 * Created by elina_000 on 07.03.2018.
 */
/* Cоздан class Point, это класс, который описывает структуру объектов (точкек)
 */
public class Point {
   /* Внутри класса описываю атрибуты, которыми обладает объект этого типа (точка)
    тоесть это кордината х и у */
    public double x;
    public double y;

    public double distance (Point p){
        return Math.sqrt((this.x-p.x)*(this.x-p.x)+(this.y-p.y)*(this.y-p.y));
    }
}

