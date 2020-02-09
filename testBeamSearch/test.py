import unittest
from start import testBeamSearch


class TestStringMethods(unittest.TestCase):

  def test_decoder(self):
      self.assertEqual(testBeamSearch("/home/kirill/Загрузки/data/test2/alphabet.txt","/home/kirill/Загрузки/data/test2/probs1.csv"), "wo bekomme ich meine ausgabenliste")

if __name__ == '__main__':
    unittest.main()