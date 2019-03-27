export default function LevenDistance(a, b) {
    const DMatrix = Array(b.length + 1).fill(null).map(() => Array(a.length + 1).fill(null));

    for (let i = 0; i <= a.length; i ++) {
      DMatrix[0][i] = i;
    }
  

    for (let k = 0; k <= b.length; k ++) {
      DMatrix[k][0] = k;
    }
  
    for (let k = 1; k <= b.length; k ++) {
      for (let i = 1; i <= a.length; i ++) {
        const indicator = a[i - 1] === b[k - 1] ? 0 : 1;
        DMatrix[k][i] = Math.min(
          DMatrix[k][i - 1] + 1, 
          DMatrix[k - 1][i] + 1, 
          DMatrix[k - 1][i - 1] + indicator, 
        );
      }
    }
  
    return DMatrix[b.length][a.length];
  }