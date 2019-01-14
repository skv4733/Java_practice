import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution
	{
		public static void main(String[] args)
			{
//				//May I have a large container of coffee
//				String str="May I have a large container of coffee";
//				//System.out.print(Pattern.compile(" ").splitAsStream(str).map(i->i.length()).findFirst().get()+".");
//				String[] st=str.split(" ");
//				for (String string : st) {
//				if(str.indexOf(string)==0) {
//					System.out.print(string.length()+".");
//				}else 
//					System.out.print(string.length());
//				}
				List<Person> persons=getperson();
				Map<Number, Long> populationChar=getChart(persons);
				populationChar.forEach((a,c)->{
					System.out.println(a+"       "+c);
				});
			}

		private static Map<Number, Long> getChart(List<Person> persons)
			{
				
				Map<Number, Long> map2=new HashMap<>();
				int startingYear=1950;
				int endingYear=2050;
				
				for (Person person : persons) {
					if (person.getBirthYear()<startingYear) {
						startingYear=person.getBirthYear();
					}
					if (person.getDeathYear()>endingYear) {
						endingYear=person.getDeathYear();
					}
					for (int i =person.getBirthYear(); i <person.getDeathYear(); i++) {
						long nofp;
						if (map2.get(i)==null) {
						nofp=(long)0.0;
						}else
						nofp=map2.get(i);
						//value 2011
						map2.put(i, nofp+1);
					}
				}
				
				return map2;
			}

		private static List<Person> getperson()
			{
				List<Person> persons=new ArrayList<>();
					persons.add(new Person("first1", 2011, 2051));
					persons.add(new Person("first2", 1988, 2051));
					persons.add(new Person("first3", 1901, 1978));
					persons.add(new Person("first4", 2011, 2051));
					persons.add(new Person("first5", 2011, 2051));
					persons.add(new Person("first6", 2011, 2051));
					persons.add(new Person("first7", 2011, 2051));
					persons.add(new Person("first8", 2011, 2051));
					return persons;
			}
	}
