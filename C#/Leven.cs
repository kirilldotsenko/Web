using System;

namespace Leven
{
    class Program
    {
        static int Main(string[] args)
        {
            string a = args[0];
            string b = args[1];
            int[,] DMatrix = new int[b.Length, a.Length];
            for (int i = 0; i <= a.Length; i++)
            {
                DMatrix[0,i] = i;
            }


            for (int k = 0; k <= b.Length; k++)
            {
                DMatrix[k,0] = k;
            }
            for (int k = 1; k <= b.Length; k++)
            {
                for (int i = 1; i <= a.Length; i++)
                {
                    int indicator = args[0][i- 1] == args[1][k - 1] ? 0 : 1;
                    DMatrix[k,i] = Math.Min(
                      DMatrix[k,i - 1] + 1,
                      Math.Min(DMatrix[k - 1,i] + 1,
                      DMatrix[k - 1,i - 1] + indicator));
                }
            }

            return DMatrix[b.Length,a.Length];
        }
    }
}
