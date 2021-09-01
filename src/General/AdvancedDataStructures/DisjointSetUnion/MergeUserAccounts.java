package General.AdvancedDataStructures.DisjointSetUnion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * You are a social media manager, and there are a lot of users on your platform with various emails attached to their accounts. 
 * For each user, there is a name and several emails associated with that user.
 * You noticed that a lot of users have multiple accounts registered with the same email, 
 * and you decided to merge some accounts according to the following rules:
 * 
 * - If two accounts have the same name and share at least one common email, they must belong to the same user, and thus can be safely merged. 
 * 	 This merged account can be merged with other accounts via the same method, 
 *   so if two accounts with the same name doesn't have a common email, 
 *   but they each have a common email with a third account with the same name, they can be merged together.
 * - Two different accounts can share the same name, as long as they cannot be linked back to the same person via email tracking.
 * - Two different accounts with different names can never be merged, even if they may share a common email.
 * 
 * After merging the accounts such that you cannot merge any more, output the final remaining accounts in a sorted order. 
 * That is, the emails of each account is sorted lexicographically, and the accounts are sorted lexicographically by name, 
 * then by the first email (if applicable).
 * 
 * Parameter
 * accounts: A matrix of strings (can be uneven). 
 * 	         Each row represent an account, with the first entry being the name and the rest being the email associated with the account.
 * Result: A matrix of strings representing the final resulting accounts, sorted.
 * 
 * Example 1:
 * Input:
 * accounts = [
 * 		["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 * 		["John", "johnsmith@mail.com", "john_work@mail.com"],
 * 		["Mary", "mary@mail.com"],
 * 		["John", "johnny@mail.com"]
 * ]
 * 
 * Output:
 * [
 * 		["John", "john_newyork@mail.com", "john_work@mail.com","johnsmith@mail.com"],
 * 		["John", "johnny@mail.com"],
 * 		["Mary", "mary@mail.com"]
 * ]
 * 
 * Explanation: The first two accounts belong to the same user because they share an email. 
 * Notice that there are two John accounts in the end, because they do not share emails with each other, even after merging.
 * 
 * Constraints
 * 1 <= len(accounts) <= 1000
 * 1 <= len(accounts[i]) <= 10
 * 1 <= len(accounts[i][j]) <= 30
 * Names consist of only English letters
 * All emails are valid. Normally emails are case insensitive, but to simplify things, assume all letters that appear in the emails are lowercase.
 * 
 * Solution
 * 
 * This is a classical DSU problem. 
 * For each user-email pair under an account, you just perform a union operation on them. 
 * In the end, you iterate though all user-email-pair and group the ones with the same ancestor together in the output.
 * Note that two emails may not belong to the same user if the username is different, 
 * so we need to store the username in the union find object as well.
 * Time Complexity: O(nlog(n))
 */
public class MergeUserAccounts {

	public static class UnionFind<T> {
        private Map<T, T> f = new HashMap<>();

        public T find(T x) {
            T y = f.getOrDefault(x, x);
            if (y != x) {
                y = find(y);
                f.put(x, y);
            }
            return y;
        }

        public void union(T x, T y) {
            f.put(find(x), find(y));
        }
    }

    public static class UserEmailPair implements Comparable<UserEmailPair> {
        public final String user;
        public final String email;

        public UserEmailPair(String user, String email) {
            this.user = user;
            this.email = email;
        }

        @Override
        public int hashCode() {
            return user.hashCode() ^ email.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (!(obj instanceof UserEmailPair))
                return false;
            
            UserEmailPair otherObj = (UserEmailPair)(obj);
            return user.equals(otherObj.user) && email.equals(otherObj.email);
        }

        @Override
        public int compareTo(UserEmailPair o) {
            if (!user.equals(o.user)) {
                return user.compareTo(o.user);
            }
            
            return email.compareTo(o.email);
        }
    }

    public static List<List<String>> mergeAccounts(List<List<String>> accounts) {
        
    	UnionFind<UserEmailPair> unionFind = new UnionFind<>();
        Set<UserEmailPair> allUserEmails = new HashSet<>();
        
        for (List<String> oneAccount : accounts) {
            String username = oneAccount.get(0);
            UserEmailPair emailParent = null;
            
            for (int i = 1; i < oneAccount.size(); i++) {
                String email = oneAccount.get(i);
                UserEmailPair userEmailPair = new UserEmailPair(username, email);
                allUserEmails.add(userEmailPair);
                
                if (emailParent == null) {
                    emailParent = userEmailPair;
                } else {
                    unionFind.union(emailParent, userEmailPair);
                }
            }
        }
        
        Map<UserEmailPair, ArrayList<UserEmailPair>> accountAssociation = new HashMap<>();
        
        for (UserEmailPair userEmailPair : allUserEmails) {
            UserEmailPair ancestor = unionFind.find(userEmailPair);
            
            if (!accountAssociation.containsKey(ancestor)) {
                accountAssociation.put(ancestor, new ArrayList<>());
            }
            
            accountAssociation.get(ancestor).add(userEmailPair);
        }
        
        List<List<String>> returnRes = new ArrayList<>();
        
        for (Map.Entry<UserEmailPair, ArrayList<UserEmailPair>> userInfo : accountAssociation.entrySet()) {
            List<String> oneUser = new ArrayList<>();
            oneUser.add(userInfo.getKey().user);
            Collections.sort(userInfo.getValue());
            
            for (UserEmailPair emailEntry : userInfo.getValue()) {
                oneUser.add(emailEntry.email);
            }
            
            returnRes.add(oneUser);
        }
        
        Collections.sort(returnRes, (a, b) -> {
            if (a.get(0).compareTo(b.get(0)) == 0)
                return a.get(1).compareTo(b.get(1));
            return a.get(0).compareTo(b.get(0));
        });
        
        return returnRes;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 4
    // John johnsmith@mail.com john_newyork@mail.com
    // John johnsmith@mail.com john_work@mail.com
    // Mary mary@mail.com
    // John johnny@mail.com
    // output:
    // John john_newyork@mail.com john_work@mail.com johnsmith@mail.com
    // John johnny@mail.com
    // Mary mary@mail.com
    
    // 6
    // James johnson@fastmail.com jamesj@company.com mrswashyhat@funmail.com
    // Jimmy johnson@fastmail.com jimmyj@company.com
    // Jimmy beastboi@funmail.com jimmyj@company.com
    // Owen ojohnson@fastmail.com owenj2@biggov.ca
    // Owen owenj2@biggov.ca owen_johnson@funmail.com
    // Jimmy beastboi@funmail.com beastboi2@funmail.com
    // output:
    // James jamesj@company.com johnson@fastmail.com mrswashyhat@funmail.com
    // Jimmy beastboi2@funmail.com beastboi@funmail.com jimmyj@company.com johnson@fastmail.com
    // Owen ojohnson@fastmail.com owen_johnson@funmail.com owenj2@biggov.ca
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int accountsLength = Integer.parseInt(scanner.nextLine());
        List<List<String>> accounts = new ArrayList<>();
        
        for (int i = 0; i < accountsLength; i++) {
            accounts.add(splitWords(scanner.nextLine()));
        }
        
        scanner.close();
        List<List<String>> res = mergeAccounts(accounts);
        
        for (List<String> row : res) {
            System.out.println(String.join(" ", row));
        }
    }
}