import check1 from "./isbn10"
import check2 from "./isbn13"

export default function isbn(num){
    var arr=num.split(/-/)
    if (arr[3]=='X'){
        num=num.replace("X",10)
    }
    
    let id=+arr[3]
    var newnum=""
    for (var i=0;i<num.length;i++){
        if(num[i]!="-"){
            newnum+=num[i]
        }
    }
    var a=newnum.length
    if (a==10){
        return check1(String(num));
    }
    if (a==13){
        return check2(String(num));
    }
}
