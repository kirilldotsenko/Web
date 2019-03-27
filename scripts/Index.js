
export default function ChangeIndex(mas,a,b){
    if(a<b){
        var mas1=[];
        for(let i=0;i<mas.length;i++){
            if(i==a){
                var num=mas[i];
            }else if(i==b){
                mas1.push(mas[i]);
                mas1.push(num);
            }else{
                mas1.push(mas[i]);
            }

        }
        return mas1;
    }
    if(a>b){
        var mas1=[];
        for(let i=0;i<mas.length;i++){
            if(i==a){
                var num=mas[i];
            }
        }
        for(let k=0;k<mas.length;k++){
            if(k==b){
                mas1.push(num);
                mas1.push(mas[k]);
            }else if(k==a){
                continue;
            }else{
                mas1.push(mas[k]);
            }
        }
        return mas1;
    }

}