export default function LevenDistance1(a, b) {
    const DMatrix = Array(b.length + 1).fill(null).map(() => Array(a.length + 1).fill(null));

    for (let i = 0; i <= a.length; i ++) {
      DMatrix[0][i] = i;
    }
  

    for (let k = 0; k <= b.length; k ++) {
      DMatrix[k][0] = k;
    }
    function matr(DMatrix,i,k){
        if(i>a.length){
            i=1;
            k++
        }
        if(k>b.length){
            return DMatrix;
        }
        const indicator = a[i - 1] === b[k - 1] ? 0 : 1;
        DMatrix[k][i] = Math.min(
          DMatrix[k][i - 1] + 1, 
          DMatrix[k - 1][i] + 1, 
          DMatrix[k - 1][i - 1] + indicator, 
        );
        return matr(DMatrix,i+1,k);
    }
    matr(DMatrix,1,1);
    console.log(DMatrix);
    console.log(DMatrix[b.length][a.length]);
    return DMatrix[b.length][a.length];
}