import java.util.*;

import static java.lang.Long.min;

public class Network {

    public static class TreeSetComparator implements Comparator<Integer>
    {
        @Override
        public int compare(Integer o1, Integer o2) {
            Node p1 = getPersonByHashCode(o1), p2 = getPersonByHashCode(o2);
            assert p1 != null;
            assert p2 != null;
            return ((Person) p1).compareTo((Person) p2);
        }
    }

    private Stack<Integer> st;
    private static HashMap<Integer, Integer> upperDepth, dp;

    private static HashMap<Integer, Boolean> check;

    public static List<Person> Persons;

    public static List<Company> Companies;

    public static List<Node> allComponents;

    public static List<List<Integer>> cnx;

    public static HashMap<Integer, TreeSet<Integer>> personNetwork;

    Network() {
        personNetwork = new HashMap<>();
        Persons = new ArrayList<>();
        Companies = new ArrayList<>();
    }

    public Integer getPerson(String name)
    {
        for(Person person : Persons)
        {
            if(person.getName().equals(name))
                return person.hashCode();
        }
        return null;
    }

    static public Person getPersonByHashCode(Integer code)
    {
        for(Person person : Persons)
        {
            if(person.hashCode() == code)
                return person;
        }
        return null;
    }

    static public Person getPersonByName(String name)
    {
        for(Person person : Persons)
        {
            if(person.getName().equals(name))
                return person;
        }
        return null;
    }

    public boolean addEdge(String name1, String name2)
    {
        Integer firstHashCode = this.getPerson(name1),
                secondHashCode = this.getPerson(name2);



        if(!personNetwork.containsKey(firstHashCode)) {
            personNetwork.put(firstHashCode, new TreeSet<>(new TreeSetComparator()));
        }
        if(!personNetwork.containsKey(secondHashCode))
            personNetwork.put(secondHashCode, new TreeSet<>(new TreeSetComparator()));

        boolean ok = checkEdge(name1, name2);
        if(ok)
            return ok;

        personNetwork.get(firstHashCode).add(secondHashCode);
        personNetwork.get(secondHashCode).add(firstHashCode);

        return false;
    }

    private boolean checkEdge(String name1, String name2)
    {
        Integer firstHashCode = this.getPerson(name1),
                secondHashCode = this.getPerson(name2);
        for(Integer code : personNetwork.get(firstHashCode))
        {
            if(code.equals(secondHashCode))
                return true;
        }
        return false;
    }

    public void addPerson(Person person) {
        Persons.add(person);
    }

    public boolean addCompany(Company company)
    {
        boolean ok = checkCompany(company);

        if(ok)
            return ok;

        Companies.add(company);
        return false;
    }

    private boolean checkCompany(Company company)
    {
        for(Company comp : Companies)
        {
            if(comp.equals(company))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        List<Integer> nodes = new ArrayList<>(),
                      grades = new ArrayList<>();
        for(Integer i : personNetwork.keySet()) {
            nodes.add(i);
            grades.add(calculateNodeImportance(Objects.requireNonNull(getPersonByHashCode(i))));
        }
        for(int i = 0;i < nodes.size() - 1; ++i)
        {
            for(int j = i + 1;j < nodes.size(); ++j)
            {
                if(grades.get(i) > grades.get(j))
                {
                    Integer aux = grades.get(i);
                    grades.set(i, grades.get(j));
                    grades.set(j, aux);

                    aux = nodes.get(i);
                    nodes.set(i, nodes.get(j));
                    nodes.set(j, aux);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append("The nodes in the network are : ");
        for(int i = 0;i < nodes.size(); ++i)
        {
            ans.append(Objects.requireNonNull(getPersonByHashCode(nodes.get(i))).getName());
            ans.append(" ");
        }
        ans.append("\nSorted by their importance\n");
        ans.append("The relationships are\n\n");
        for(Integer i : personNetwork.keySet())
        {
            for(Integer j : personNetwork.get(i))
            ans.append(getPersonByHashCode(i).getName() + " -> " + getPersonByHashCode(j).getName() + "\n");
        }
        return ans.toString();

    }

    private int calculateNodeImportance(Node node) {
        String name = node.getName();
        Person person = getPersonByName(name);
        int thisCode = person.hashCode();
        return personNetwork.get(thisCode).size();
    }

    static public List<Integer> findArticulationPoints()
    {
        List<Integer> answer = new ArrayList<>();
        upperDepth = new HashMap<>();
        dp = new HashMap<>();
        check = new HashMap<>();
        Person p1 = getPersonByName(Persons.get(0).getName());
        Integer root = p1.hashCode();
        dfs1(root, 1, -1);
        for(Integer node : personNetwork.keySet())
        {
            int cnt = 0;
//            System.out.println(dp.get(node) + " " + upperDepth.get(node) + " " + getPersonByHashCode(node).getName());
            for(Integer adjNode : personNetwork.get(node))
            {
                if(dp.get(adjNode) > dp.get(node) && dp.get(node) <= upperDepth.get(adjNode) && !root.equals(node))
                {
                    answer.add(node);
                    break;
                }
                else
                    cnt++;

                if(root.equals(node) && cnt == 2)
                {
                    answer.add(node);
                    break;
                }
            }
        }
        return answer;
    }

    private static void dfs1(Integer x, int depth, int father)
    {
        check.put(x, true);
        upperDepth.put(x, depth);
        dp.put(x, depth);
        for(Integer i : personNetwork.get(x))
        {
            if(!check.containsKey(i)) {
                dfs1(i, depth + 1, x);
                if(upperDepth.get(i) < upperDepth.get(x))
                    upperDepth.put(x, upperDepth.get(i));
            }
            else {
                if(!i.equals(father) && upperDepth.get(x) > dp.get(i)) {
                    upperDepth.put(x, dp.get(i));
                }
            }

        }
    }

    private void dfs2(Integer x, int depth, int father)
    {
        st.add(x);
        check.put(x, true);
        upperDepth.put(x, depth);
        dp.put(x, depth);
        for(Integer i : personNetwork.get(x))
        {
            if(!check.containsKey(i))
            {
                dfs2(i, depth + 1, father);
                if(upperDepth.get(i) < upperDepth.get(x))
                    upperDepth.put(x, upperDepth.get(i));
                if (dp.get(x) <= upperDepth.get(i)) {

                    cnx.add(new ArrayList<>());
                    int curr = cnx.size() - 1;
                    while(!st.empty() && !Objects.equals(st.peek(), i))
                    {
                        cnx.get(curr).add(st.peek());
                        st.pop();
                    }
                    st.pop();
                    cnx.get(curr).add(x);
                    cnx.get(curr).add(i);
                }
            }
            else {
                if (father != i && dp.get(i) < upperDepth.get(x))
                    upperDepth.put(x, (int) min(upperDepth.get(x), dp.get(i)));
            }
        }
    }
    public List<List<Integer>> findBiconnectedComponents()
    {
        cnx = new ArrayList<>();
        upperDepth = new HashMap<>();
        dp = new HashMap<>();
        check = new HashMap<>();
        st = new Stack<>();
        Person p1 = getPersonByName(Persons.get(0).getName());
        Integer root = p1.hashCode();
        dfs2(root, 1, -1);
        return cnx;
    }

    public Stack<Integer> getSt() {
        return st;
    }

    public void setSt(Stack<Integer> st) {
        this.st = st;
    }

    public static HashMap<Integer, Integer> getUpperDepth() {
        return upperDepth;
    }

    public static void setUpperDepth(HashMap<Integer, Integer> upperDepth) {
        Network.upperDepth = upperDepth;
    }

    public static HashMap<Integer, Integer> getDp() {
        return dp;
    }

    public static void setDp(HashMap<Integer, Integer> dp) {
        Network.dp = dp;
    }

    public static HashMap<Integer, Boolean> getCheck() {
        return check;
    }

    public static void setCheck(HashMap<Integer, Boolean> check) {
        Network.check = check;
    }

    public static List<Person> getPersons() {
        return Persons;
    }

    public static void setPersons(List<Person> persons) {
        Persons = persons;
    }

    public static List<Company> getCompanies() {
        return Companies;
    }

    public static void setCompanies(List<Company> companies) {
        Companies = companies;
    }

    public static List<Node> getAllComponents() {
        return allComponents;
    }

    public static void setAllComponents(List<Node> allComponents) {
        Network.allComponents = allComponents;
    }

    public static List<List<Integer>> getCnx() {
        return cnx;
    }

    public static void setCnx(List<List<Integer>> cnx) {
        Network.cnx = cnx;
    }

    public static HashMap<Integer, TreeSet<Integer>> getPersonNetwork() {
        return personNetwork;
    }

    public static void setPersonNetwork(HashMap<Integer, TreeSet<Integer>> personNetwork) {
        Network.personNetwork = personNetwork;
    }

    public void mergeLists() {
        allComponents = new ArrayList<>();
        allComponents.addAll(Persons);
        allComponents.addAll(Companies);
    }
}
