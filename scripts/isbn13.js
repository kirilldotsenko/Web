export default function check2(num){
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
    if ((a==13) && (arr.length==5) && (arr[4]<10) && (arr[0]==978 || arr[0]==979)){
        let sum=0
        let newnumb=+newnum
        var count=1
        while (newnumb>0){
            if (count%2==0){
                sum+=(newnumb%10)*3
                Math.floor(newnumb/10)
                count++

            }else{
                sum+=(newnumb%10)*1
                Math.floor(newnumb/10)
                count++
            }
        
        if(sum%10==0){
            return "ISBN-13"
        }

        }



    }
}

