import java.util.TreeMap;

public class Converter {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();

    public Converter() {
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);

        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");
    }

    public boolean isRomanNumber(String number) {
        return romanKeyMap.containsKey(number.charAt(0));
    }

    public int romanToInt(String inputString) {
        char[] arr = inputString.toCharArray();
        int arabian;
        int result = romanKeyMap.get(arr[inputString.length() - 1]);
        for (int i = inputString.length() - 2; i >= 0; i--) {
            arabian = romanKeyMap.get(arr[i]);

            if (arabian < romanKeyMap.get(arr[i + 1])) {
                result -= arabian;
            } else {
                result += arabian;
            }
        }
        return result;
    }

    public String intToRoman(int inputNumber) throws Exception {
        if (inputNumber < 1) {
            throw new Exception("Is not in range Roman numbers");
        }

        StringBuilder romanNumber = new StringBuilder();
        int arabianNumber;

        do {
            arabianNumber = arabianKeyMap.floorKey(inputNumber);
            romanNumber.append(arabianKeyMap.get(arabianNumber));
            inputNumber -= arabianNumber;
        } while (inputNumber != 0);

        return romanNumber.toString();
    }
}
