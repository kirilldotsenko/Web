export default function check1(num){
    var arr=num.split(/-/)
    if (arr[3]=='X'){
        num=num.replace("X",10)
    }
    
    var id=+arr[3]
    var newnum=""
    for (var i=0;i<num.length;i++){
        if(num[i]!="-"){
            newnum+=num[i]
        }
    }
    var a=newnum.length
    if ((a==10) && (arr.length==4) && (arr[3]<10)){
        let sum=0
        var newnumb=Number(newnum.substring(0, newnum.length - 1))
        var fact=2
        while (newnumb>0){
            sum+=(newnumb%10)*fact
            newnumb=Math.floor(newnumb/10)
            fact++
        }
        if((sum+id)%11==0){
            return "ISBN-10";
        }
    }
    
}
