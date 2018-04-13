public class Data {
    private Integer time;
    private String opcode;
    private String orderId;
    private String side;
    private Integer price;
    private Integer size;


    public Data(Integer time){
        this.time = time;
    }

    public void setTime(Integer time){
        this.time = time;
    }

    public  Integer setOpcode(String opcode){
        if(opcode.equals("ADD")){
            this.opcode = opcode;
            return 1;
        }
        else if(opcode.equals("RESET")){
            this.opcode = opcode;
            return 2;
        }
        else if(opcode.equals("CANCEL")){
            this.opcode = opcode;
            return 3;
        }
        else if(opcode.equals("MODIFY")){
            this.opcode = opcode;
            return 4;
        }
        else return -1;
    }

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }

    public Integer setSide(String side){
        if(side.equals("B")){
            this.side = side;
            return 1;
        }
        else if(side.equals("S")){
            this.side = side;
            return 2;
        }
        else return -1;
    }

    public Integer setPrice(Integer price){
        if (price >= 1000000){
            System.out.println("Invalid price");
            return -1;
        }
        else this.price = price;
        return 1;
    }

    public Integer setSize(Integer size){
        if (size >= 1000000){
            System.out.println("Invalid price");
            return -1;
        }
        else this.size = size;
        return 1;
    }

    public Integer getTime(){
        return this.time;
    }

    public String getOpcode(){
        return this.opcode;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getSide(){
        return  this.side;
    }

    public Integer getPrice(){
        return this.price;
    }

    public Integer getSize(){
        return this.size;
    }
}
