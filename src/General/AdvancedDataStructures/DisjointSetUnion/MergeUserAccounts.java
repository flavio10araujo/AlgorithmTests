package General.AdvancedDataStructures.DisjointSetUnion;

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

	
}