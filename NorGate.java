class data{
    //sensor values
    public int x0[]=new int[5];
    public int x1[]=new int[5];
    public int x2[]=new int[5];
    //output z
    public int z[]=new int[5];
    //learning rate r
    public double r=0.1;
    //sum and n
    public double s[]=new double[5];

    public int n[]=new int[5];
    public int e[]=new int[5];

    //weights
    public double w0[]=new double[5];
    public double w1[]=new double[5];
    public double w2[]=new double[5];
    //xi*wi=ci
    public double c0[]=new double[5];
    public double c1[]=new double[5];
    public double c2[]=new double[5];
    public void x(){
           x0[1]=1;x0[2]=1;x0[3]=1;x0[4]=1;
           x1[1]=0;x1[2]=0;x1[3]=1;x1[4]=1;
           x2[1]=0;x2[2]=1;x2[3]=0;x2[4]=1;
           z[1]=1;z[2]=1;z[3]=1;z[4]=0;
    }
    public void w(){
        w0[1]=0.3;w0[2]=0.4;w0[3]=0.5;w0[4]=0.5;
        w1[1]=0.1;w1[2]=0;w1[3]=0.1;w1[4]=0.1;
        w2[1]=0.1;w2[2]=0.1;w2[3]=0.2;w2[4]=0.2;
    }

    public void xiwi(){
        for(int i=1;i<=4;i++){
            c0[i]=w0[i]*x0[i];
            c1[i]=w1[i]*x1[i];
            c2[i]=w2[i]*x2[i];
        }
        for(int i=1;i<=4;i++){
            s[i]=c0[i]+c1[i]+c2[i];
        }
    }
    public void display(){
        for(int i=1;i<=4;i++){
            System.out.println("value at "+i+" is "+w0[i]+" , "+w1[i]+" , "+w2[i]+" , error is "+e[i]+" , "+n[i]);
        }
    }
    //the classification value (like output 1 if > 0.5)
    public void N(){
        for (int i=1;i<=4;i++){
            if(s[i]>0.5)
                n[i]=1;
            else
                n[i]=0;
        }
        //calculating the error and putting into array e[]
        for (int i=1;i<=4;i++){
            e[i]=z[i]-n[i];
        }
    }
    public void update(){
        for (int i=1;i<=4;i++){
            double c=r*e[i];//c is correction
            //weights updated
            w0[i]=w0[i]+c;
            w1[i]=w1[i]+c;
            w2[i]=w2[i]+c;
        }
    }
}
public class NorGate {
    static data d=new data();
    public static void main(String [] args){
        d.x();
        d.w();
        d.xiwi();
        d.N();
        int count=0;
        for(int i=0;i<100;i++){
            int sum=0;
            count++;
            for(int j=1;j<=4;j++){
                sum+=d.e[j];
            }
            if(sum==0){
                System.out.println("hard work over");
                d.display();
                break;
            }
            else {
                d.update();
                d.xiwi();
                d.N();
            }
        }
        System.out.println(count);
        //d.display();
    }
}