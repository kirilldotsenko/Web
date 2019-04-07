using System;

namespace Is10
{
    public class Isbn10
    {
        public int Isb10(string numb)
        {
            string isbn10 = numb;
            string[] arr = isbn10.Split('-');
            if (arr[3] == "X")
                arr[3] = "10";
            isbn10 = isbn10.Replace("-", "");
            int id = Int32.Parse(arr[3]);
            int lenArr = arr.Length;
            int lenStr = isbn10.Length;
            if ((lenStr == 10) && (lenArr == 4) && (id < 10))
            {
                double sum = 0;
                double newnumb = Int32.Parse(isbn10.Substring(0, 9));
                int fact = 2;
                while (newnumb > 0)
                {
                    sum += (newnumb % 10) * fact;
                    newnumb = Math.Floor(newnumb / 10);
                    fact++;
                }
                if ((sum + id) % 11 == 0)
                {
                    Console.Write("ISBN-10");
                }
            }
            Console.ReadKey();
            return 1;
        }
    }
}