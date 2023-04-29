public class BaseConverter
{
    public long convertFromBaseTwo(String number)
    {
        int numberOfBytes = number.length() - 1;
        long answer = 0;
        for(int i = 0;i < number.length(); ++i)
        {
            answer = answer +  (number.charAt(i) - '0') * (1L << numberOfBytes);
            numberOfBytes--;
        }
        return answer;
    }

    public long convertFromBaseSixteen(String number)
    {
        int numberOfBytes = number.length() - 1;
        long answer = 0, p = 1;
        for(int i = number.length() - 1;i >= 0; --i)
        {
            int value = (number.charAt(i) >= '0' && number.charAt(i) <= '9' ? number.charAt(i) - '0' : number.charAt(i) - 'A' + 10);
            answer = answer + p * value;
            p *= 16;
        }
        return answer;
    }

}