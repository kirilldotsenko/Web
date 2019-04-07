using System;

namespace Is13
{
    class Isbn13
    {
        public int Isb13(string numb)
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
                double newnumb = Int32.Parse(isbn10.Substring(0, 12));
                int count = 2;
                while (newnumb > 0)
                {
                    if (count % 2 == 0)
                    {
                        sum += (newnumb % 10) * 3;
                        Math.Floor(newnumb / 10);
                        count++;
                    }
                    else
                    {
                        sum += (newnumb % 10) * 1;
                        Math.Floor(newnumb / 10);
                        count++;
                    }

                    if (sum % 10 == 0)
                    {
                        Console.Write("ISBN-13");
                    }
                }
            }
            Console.ReadKey();
            return 1;
        }
    }
}