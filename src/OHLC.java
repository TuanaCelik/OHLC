import java.util.ArrayList;
import java.util.*;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class OHLC {
    private Integer open;
    private Integer high;
    private Integer low;
    private Integer close;
    public OHLC(Integer open, Integer high, Integer low, Integer close){
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    private static void produceGraph(ArrayList<Data> trades){
        Integer open = trades.get(0).getTime();
        Integer start = 0;
        for(int i = 1; i < trades.size(); i++){
            if(trades.get(i).getTime() - open >= 300){
                prduceCandle(start, i);
            }
            start = i;
        }
    }

    private  static void prduceCandle(Integer start, Integer end){
        Integer startTime = trades.get(start).getTime();
        Integer open = trades.get(start).getPrice();
        Integer high = open;
        Integer low = open;
        for(int i = start + 1; i <=end; i++){
            if(trades.get(i).getPrice() > high) high = trades.get(i).getPrice();
            if(trades.get(i).getPrice() < low) low = trades.get(i).getPrice();
        }
        Integer close = trades.get(end).getPrice();
        OHLC ohlc = new OHLC(open, high, low, close);
        System.out.println(startTime + " " + open + " " + high + " " + low + " " + close);
    }

    private static ArrayList<Data> trades = new ArrayList<>();
    private static ArrayList<OHLC> ohlc = new ArrayList<>();
    private static Integer counter = 0;
    private static HashMap<String, Integer> ids = new HashMap<>();

    private static ArrayList<Data> reset(){
        System.out.println("Trades have been reset");
        ids.clear();
        return new ArrayList<Data>();
    }

    private static void cancel(String id){
        System.out.println("Cancelling order " + id);
        Integer index = ids.get(id);
        trades.get(index).setSize(0);
        trades.get(index).setPrice(0);
        ids.remove(id);
    }

    private static boolean check(Integer opcode){
        if(opcode > 0) return false;
        else return true;
    }

    private static void modify(String orderid, Integer size, Integer price){
        Integer index = ids.get(orderid);
        trades.get(index).setSize(size);
        trades.get(index).setPrice(price);
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Boolean start = true;
        Boolean next = true;
        while(start){
            if(next){
                next = false;
                String first = in.next();
                if(first.equals("done")){
                    break;
                }
                Data data = new Data(Integer.parseInt(first));
                Integer opcode = data.setOpcode(in.next());
                if (opcode > 0){
                    if(opcode == 1){
                        data.setOrderId(in.next());

                        while(check(data.setSide(in.next()))){}

                        data.setSize(Integer.parseInt(in.next()));
                        data.setPrice(Integer.parseInt(in.next()));
                        trades.add(data);
                        ids.put(data.getOrderId(), counter);
                        counter++;
                    }
                    else if(opcode == 2){
                        trades = reset();
                    }
                    else if(opcode == 3){
                        cancel(in.next());
                    }
                    else if(opcode == 4){
                        modify(in.next(), in.nextInt(), in.nextInt());
                    }
                    next = true;
                }else{
                    System.out.println("Enter valid opCode");
                    while(check(data.setOpcode(in.next()))){

                    }
                    System.out.println("Thanks");

                }
            }
        }

//        System.out.println("Now produce graph");
//        for(int i = 0; i < trades.size(); i++){
//            System.out.println(trades.get(i).getTime());
//            System.out.println(trades.get(i).getSize());
//            System.out.println(trades.get(i).getPrice());
//        }

        produceGraph(trades);
    }
}
