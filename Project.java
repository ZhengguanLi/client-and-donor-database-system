
//A sample Java program using JDBC to connect to an Oracle database,
//create a table student, insert 2 rows into student and
//display the IDs and names of all students
//package test;
import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class Project {
	public static void main(String[] args) {
		// Step 1. Loading a database driver
		String sourceURL = "";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (Exception x) {
			System.out.println("Unable to load the driver class!");
		}
		int option = 0;
		while (option != 20) {
			// Step 2. Creating an Oracle JDBC Connection. The following example assumes
			// that the login name is smith1234 and the password is johnsmith
			try {
				Connection conn = DriverManager.getConnection(sourceURL, " li3597", "URdu2Nv3");
				// Step 3. Creating a JDBC Statement object
				Statement stmt = conn.createStatement();
				Statement stmt2 = conn.createStatement();
				// Step 4. Executing the SQL statements with the Statement object

				option = 0;
				System.out.println("1. Enter a new team into the database.\n"
						+ "2. Enter a new client into the database and associate him or her with one or more teams.\n"
						+ "3. Enter a new volunteer into the database and associate him or her with one or more teams.\n"
						+ "4. Enter the number of hours a volunteer worked this month for a particular team.\n"
						+ "5. Enter a new employee into the database and associate him or her with one or more teams.\n"
						+ "6. Enter an expense charged by an employee.\n"
						+ "7. Enter a new organization and associate it to one or more PAN teams.\n"
						+ "8. Enter a new donor and associate him or her with several donations.\n"
						+ "9. Enter a new organization and associate it with several donations.\n"
						+ "10. Retrieve the name and phone number of the doctor of a particular client.\n"
						+ "11. Retrieve the total amount of expenses charged by each employee for a particular period of time. The list should be sorted by the total amount of expenses.\n"
						+ "12. Retrieve the list of volunteers that are members of teams that support a particular client.\n"
						+ "13. Retrieve the names and contact information of the clients that are supported by teams sponsored by an organization whose name starts with a letter between B and K. The client list should be sorted by name.\n"
						+ "14. Retrieve the name and total amount donated by donors that are also employees. The list should be sorted by the total amount of the donations, and indicate if each donor wishes to remain anonymous.\n"
						+ "15. For each team, retrieve the name and associated contact information of the volunteer that has worked the most total hours between March and June.\n"
						+ "16. Increase the salary by 10% of all employees to whom more than one team must report.\n"
						+ "17. Delete all clients who do not have health insurance and whose value of importance for transportation is less than 5.\n"
						+ "18. Import: Enter new teams from a data file until the file is empty (the user should be asked to enter the input file name).\n"
						+ "19. Export: Retrieve names and mailing addresses of all people on the mailing list and output them to a data file instead of screen (the user should be asked to enter the output file name).\n"
						+ "20. Quit");
				Scanner sc = new Scanner(System.in);
				option = Integer.parseInt(sc.nextLine());

				// 1. Enter a new team into the database.
				if (option == 1) {
					System.out.println("Please enter team name: ");
					String team_name = sc.nextLine();

					System.out.println("Please enter team type: ");
					String type = sc.nextLine();

					System.out.println("Please enter date formed: ");
					String date_formed = sc.nextLine();

					String inst = "insert into team values('" + team_name + "'," + "'" + type + "', " + "'"
							+ date_formed + "')";
					try {
						stmt.executeUpdate(inst);
					} catch (SQLException e) {
						System.err.println("SQLException: " + e.getMessage());
					}
				}

				// 2. Enter a new client into the database and associate him or her with one or
				// more teams.
				if (option == 2) {
					System.out.println("Please enter SSN:");
					String SSN = sc.nextLine();
					ResultSet rset = stmt.executeQuery("select SSN from person where SSN = '" + SSN + "'");
					// if the person does not exist
					if (!rset.next()) {
						System.out.println("Please enter name:");
						String name = sc.nextLine();

						System.out.println("Please enter birth date:");
						String birth_date = sc.nextLine();

						System.out.println("Please enter race:");
						String race = sc.nextLine();

						System.out.println("Please enter gender:");
						String gender = sc.nextLine();

						System.out.println("Please enter profession:");
						String profession = sc.nextLine();

						System.out.println("Please enter mailing address:");
						String mailing_address = sc.nextLine();

						System.out.println("Please enter email address:");
						String email_address = sc.nextLine();

						System.out.println("Please enter home:");
						String home = sc.nextLine();

						System.out.println("Please enter work:");
						String work = sc.nextLine();

						System.out.println("Please enter cell phone number:");
						String cell_phone = sc.nextLine();

						System.out.println("Please enter if the client is on the mailling list(0 for no, 1 for yes): ");
						String if_on_mailling_list = sc.nextLine();
						String inst = "insert into person values('" + SSN + "','" + name + "'," + "'" + birth_date
								+ "'," + "'" + race + "'," + "'" + gender + "'," + "'" + profession + "'," + "'"
								+ mailing_address + "'," + "'" + email_address + "'," + "'" + home + "', " + "'" + work
								+ "'," + "'" + cell_phone + "'," + "'" + if_on_mailling_list + "')";

						try {
							stmt.executeUpdate(inst);
						} catch (SQLException e) {
							System.err.println("SQLException: " + e.getMessage());
						}
					}
					System.out.println("Please enter the name of doctor:");
					String name_of_doctor = sc.nextLine();

					System.out.println("Please enter the name of attorney:");
					String name_of_attorney = sc.nextLine();

					System.out.println("Please enter the phone number of doctor:");
					String phone_number_of_doctor = sc.nextLine();

					System.out.println("Please enter the phone number of attorney:");
					String phone_number_of_attorney = sc.nextLine();

					System.out.println("Please enter the date first assigned:");
					String date_first_assigned = sc.nextLine();

					String inst = "insert into client values('" + SSN + "','" + name_of_doctor + "'," + "'"
							+ phone_number_of_doctor + "'," + "'" + name_of_attorney + "'," + "'"
							+ phone_number_of_attorney + "'," + "'" + date_first_assigned + "')";
					try {
						stmt.executeUpdate(inst);
					} catch (SQLException e) {
						System.err.println("SQLException: " + e.getMessage());
					}

					ResultSet rset_team = stmt.executeQuery("select name from team");
					System.out.println("Please choose one or more associated teams which will care you:");
					while (rset_team.next()) {
						System.out.println(rset_team.getString(1));// output all the team name to let client choose
					}
					System.out.println("Please enter the name of one or more team you choose,enter Q to exit:");
					String team_name = sc.nextLine();

					while (!team_name.equals("Q")) {
						System.out.println(
								"Please enter if the team is active or not(0 for not active and 1 for active): ");
						String is_active = sc.nextLine();
						String sqlInsert2;
						sqlInsert2 = "insert into care values('" + team_name + "','" + SSN + "','" + is_active + "')";
						try {
							stmt.executeUpdate(sqlInsert2);
						} catch (SQLException e) {
							System.err.println("SQLException: " + e.getMessage());
						}
						System.out
								.println("Please enter the next name of one or more team you choose, enter Q to quit:");
						team_name = sc.nextLine();
					}
				}

				// 3. Enter a new volunteer into the database and associate him or her with one
				// or more teams.
				if (option == 3) {
					System.out.println("Please enter SSN of volunteer:");
					String SSN = sc.nextLine();
					ResultSet rset = stmt.executeQuery("select SSN from person where SSN = '" + SSN + "'");
					// check if the SSN has already exist
					if (!rset.next()) {
						System.out.println("Please enter the name of volunteer:");
						String name = sc.nextLine();

						System.out.println("Please enter the birth date:");
						String birth_date = sc.nextLine();

						System.out.println("Please enter the race:");
						String race = sc.nextLine();

						System.out.println("Please enter the gender:");
						String gender = sc.nextLine();

						System.out.println("Please enter the profession:");
						String profession = sc.nextLine();

						System.out.println("Please enter the mailing address:");
						String mailing_address = sc.nextLine();

						System.out.println("Please enter the email:");
						String email_address = sc.nextLine();

						System.out.println("Please enter the home:");
						String home = sc.nextLine();

						System.out.println("Please enter the work:");
						String work = sc.nextLine();

						System.out.println("Please enter the cell phone:");
						String cell_phone = sc.nextLine();

						System.out.println("Please enter if it is on mailling list or not(0 for no, 1 for yes):");
						String if_on_mailling_list = sc.nextLine();

						String inst = "insert into person values('" + SSN + "','" + name + "'," + "'" + birth_date
								+ "'," + "'" + race + "'," + "'" + gender + "'," + "'" + profession + "'," + "'"
								+ mailing_address + "'," + "'" + email_address + "'," + "'" + home + "'," + "'" + work
								+ "'," + "'" + cell_phone + "'," + "'" + if_on_mailling_list + "')";
						try {
							stmt.executeUpdate(inst);
						} catch (SQLException e) {
							System.err.println("SQLException: " + e.getMessage());
						}
					}
					System.out.println("Please enter date of join:");
					String date_of_join_pan = sc.nextLine();

					System.out.println("Please enter date of training:");
					String date_of_training = sc.nextLine();

					System.out.println("Please enter the location of training:");
					String location_of_training = sc.nextLine();

					String inst = "insert into volunteer values('" + SSN + "','" + date_of_join_pan + "'," + "'"
							+ date_of_training + "'," + "'" + location_of_training + "')";

					try {
						stmt.executeUpdate(inst);
					} catch (SQLException e) {
						System.err.println("SQLException: " + e.getMessage());
					}

					// associate him/her with one or more team and add the SSN and team into the
					// care relation
					ResultSet rset_team = stmt.executeQuery("select name from team");
					System.out.println("Please choose one or more associated teams which you want to serve");
					while (rset_team.next()) {
						System.out.println(rset_team.getString(1)); // output all the team name to let client choose
					}

					String team_name = null;
					System.out.println("Please enter the name of one or more team you choose,enter Q to quit:");
					team_name = sc.nextLine();
					while (!team_name.equals("Q")) {
						String sqlInsert2;
						sqlInsert2 = "insert into serve values('" + team_name + "','" + SSN + "','" + "1" + "')";
						try {
							stmt.executeUpdate(sqlInsert2);
						} catch (SQLException e) {
							System.err.println("SQLException: " + e.getMessage());
						}
						System.out
								.println("Please enter the next name of one or more team you choose,enter Q to quit:");
						team_name = sc.nextLine();// get the next command
					}
				}

				// 4. Enter the number of hours a volunteer worked this month for a particular
				// team.
				if (option == 4) {
					System.out.println("Please enter the SSN of this volunteer:");
					String SSN = sc.nextLine();

					// output the list of team that the client is serving on
					System.out.println("Please choose team from below:");
					ResultSet rset_team = stmt.executeQuery("select team_name from serve where SSN = '" + SSN + "'");
					while (rset_team.next()) {
						System.out.println(rset_team.getString(1));
					}
					System.out.println("Please enter the team name the volunteer work for:");
					String team_name = sc.nextLine();

					System.out.println("Please enter current month:");
					String month_str = sc.nextLine();
					int month = Integer.valueOf(month_str);

					System.out.println("Please enter the number of hour that the volunteer worked:");
					String hour_str = sc.nextLine();
					int hour = Integer.valueOf(hour_str);

					String inst = "insert into serve_month_hour values('" + SSN + "','" + team_name + "','" + month
							+ "','" + hour + "')";
					try {
						stmt.executeUpdate(inst);
					} catch (SQLException ex) {
						System.err.println("SQLException: " + ex.getMessage());
					}
				}

				// 5. Enter a new employee into the database and associate him or her with one
				// or more teams.
				if (option == 5) {
					System.out.println("Please enter the SSN of employee:");
					String SSN = sc.nextLine();

					ResultSet rset = stmt.executeQuery("select SSN from person where SSN = '" + SSN + "'");

					if (!rset.next()) {// there is no such the person so input the person information

						System.out.println("Please enter name:");
						String name = sc.nextLine();

						System.out.println("Please enter birth date:");
						String birth_date = sc.nextLine();

						System.out.println("Please enter race:");
						String race = sc.nextLine();

						System.out.println("Please enter gender:");
						String gender = sc.nextLine();

						System.out.println("Please enter profession:");
						String profession = sc.nextLine();

						System.out.println("Please enter mailing address:");
						String mailing_address = sc.nextLine();

						System.out.println("Please enter email:");
						String email_address = sc.nextLine();

						System.out.println("Please enter home:");
						String home = sc.nextLine();

						System.out.println("Please enter work:");
						String work = sc.nextLine();

						System.out.println("Please enter cell phone:");
						String cell_phone = sc.nextLine();

						System.out.println("Please enter if it is on mailling list or not:(0 for not, 1 for yes)");
						String if_on_mailling_list = sc.nextLine();

						String inst = "insert into person values('" + SSN + "','" + name + "'," + "'" + birth_date
								+ "'," + "'" + race + "'," + "'" + gender + "'," + "'" + profession + "'," + "'"
								+ mailing_address + "'," + "'" + email_address + "'," + "'" + home + "'," + "'" + work
								+ "'," + "'" + cell_phone + "'," + "'" + if_on_mailling_list + "')";

						try {
							stmt.executeUpdate(inst);
						} catch (SQLException e) {
							System.err.println("SQLException: " + e.getMessage());
						}

						System.out.println("Please enter salary:");
						String salary = sc.nextLine();

						System.out.println("Please enter marital status:");
						String marital_status = sc.nextLine();

						System.out.println("Please enter hire date:");
						String hire_date = sc.nextLine();

						inst = "insert into employee values('" + SSN + "','" + salary + "'," + "'" + marital_status
								+ "'," + "'" + hire_date + "')";

						try {
							stmt.executeUpdate(inst);
						} catch (SQLException e) {
							System.err.println("SQLException: " + e.getMessage());
						}
					}
					ResultSet rset_team = stmt.executeQuery("select name from team");

					System.out.println("Please choose one team which will have a report to you:");
					while (rset_team.next()) {
						System.out.println(rset_team.getString(1));// output all the team name to let client choose
					}

					System.out.println("Please enter the name of one or more team you choose, enter Q to quit:");
					String team_name = sc.nextLine();
					while (!team_name.equals("Q")) {
						String sqlInsert2;
						System.out.println("Please enter the date to report:");
						String date_ = sc.nextLine();

						System.out.println("Please enter the description for report:");
						String description_ = sc.nextLine();

						sqlInsert2 = "insert into report values('" + team_name + "','" + SSN + "','" + date_ + "','"
								+ description_ + "')";

						try {
							stmt.executeUpdate(sqlInsert2);
						} catch (SQLException e) {
							System.err.println("SQLException: " + e.getMessage());
						}

						System.out.println("Please enter the next name of a team, enter Q to quit:");
						team_name = sc.nextLine();
					}
				}

				// 6. Enter an expense charged by an employee.
				if (option == 6) {
					System.out.println("Please enter the SSN of the employee:");
					String SSN = sc.nextLine();

					System.out.println("Please enter the date of this expense:");
					String date = sc.nextLine();

					System.out.println("Please enter the amount of the expense:");
					String amount = sc.nextLine();

					System.out.println("Please enter the description of the expense:");
					String description = sc.nextLine();

					String inst = "insert into expense values('" + SSN + "','" + date + "','" + amount + "','"
							+ description + "')";

					try {
						stmt.executeUpdate(inst);
					} catch (SQLException e) {
						System.err.println("SQLException: " + e.getMessage());
					}
				}

				// 7. Enter a new organization and associate it to one or more PAN teams.
				if (option == 7) {
					System.out.println("Please enter the name of the organization:");
					String organization_name = sc.nextLine();

					System.out.println("Please enter the mailling address:");
					String mailling_address = sc.nextLine();

					System.out.println("Please enter the phone number of the organization:");
					String phone_number = sc.nextLine();

					System.out.println("Please enter the name of contact person of the organization:");
					String contact_person = sc.nextLine();

					System.out.println("Please enter the if the organization is anonymous(0 for no, 1 for yes):");
					String is_anonymous = sc.nextLine();

					String sqlInsert2 = "insert into organization values('" + organization_name + "','"
							+ mailling_address + "','" + phone_number + "','" + contact_person + "','" + is_anonymous
							+ "')";

					try {
						stmt.executeUpdate(sqlInsert2);
					} catch (SQLException e) {
						System.err.println("SQLException: " + e.getMessage());
					}
					System.out.println("Please choose team from below who is associated with the organization:");
					ResultSet rset_team = stmt.executeQuery("select name from team");

					while (rset_team.next()) {
						System.out.println(rset_team.getString(1));// output all the team name to let client choose
					}
					System.out.println("Please choose the team or enter Q to quit: ");
					String team_name = null;
					team_name = sc.nextLine();
					while (!team_name.equals("Q")) {
						String inst = "insert into sponsor values('" + team_name + "','" + organization_name + "')";

						try {
							// stmt.executeUpdate(sqlCreate);
							stmt.executeUpdate(inst);
							// stmt.executeUpdate(sqlInsert2);
						} catch (SQLException e) {
							System.err.println("SQLException: " + e.getMessage());
						}

						System.out.println(
								"Please enter the next name of one or more team you choose, or enter Q to quit");
						team_name = sc.nextLine();// get the next command
					}

				}

				// 8. Enter a new donor and associate him or her with several donations.
				if (option == 8) {
					System.out.println("Please enter SSN of donor:");
					String SSN = sc.nextLine();
					String name;
					ResultSet rset = stmt.executeQuery("select SSN from person where SSN = '" + SSN + "'");

					if (!rset.next()) {// there is no such the person so input the person information
						System.out.println("Please enter name:");
						name = sc.nextLine();

						System.out.println("Please enter birth date:");
						String birth_date = sc.nextLine();

						System.out.println("Please enter race:");
						String race = sc.nextLine();

						System.out.println("Please enter gender:");
						String gender = sc.nextLine();

						System.out.println("Please enter profession:");
						String profession = sc.nextLine();

						System.out.println("Please enter mailing address:");
						String mailing_address = sc.nextLine();

						System.out.println("Please enter email:");
						String email_address = sc.nextLine();

						System.out.println("Please enter home phone number:");
						String home = sc.nextLine();

						System.out.println("Please enter work phone number:");
						String work = sc.nextLine();

						System.out.println("Please enter cell phone number:");
						String cell_phone = sc.nextLine();

						System.out.println("Please enter if it is on mailling list(0 for no, 1 for yes):");
						String if_on_mailling_list = sc.nextLine();

						String inst = "insert into person values('" + SSN + "','" + name + "'," + "'" + birth_date
								+ "'," + "'" + race + "'," + "'" + gender + "'," + "'" + profession + "'," + "'"
								+ mailing_address + "'," + "'" + email_address + "'," + "'" + home + "'," + "'" + work
								+ "'," + "'" + cell_phone + "'," + "'" + if_on_mailling_list + "')";

						try {
							stmt.executeUpdate(inst);
						} catch (SQLException e) {
							System.err.println("SQLException: " + e.getMessage());
						}

					}

					System.out.println("Please enter if the donor is anonymous(0 for no, 1 for yes):");
					String if_anonymous = sc.nextLine();

					String inst = "insert into donor values('" + SSN + "','" + if_anonymous + "')";
					try {
						stmt.executeUpdate(inst);
					} catch (SQLException e) {
						System.err.println("SQLException: " + e.getMessage());
					}

					String date = null;
					System.out.println("Please enter the date of the donation or enter Q to quit:");
					date = sc.nextLine();

					while (!date.equals("Q")) {
						System.out.println("Please enter the amount of the donation:");
						String amount = sc.nextLine();

						System.out.println("Please enter the type of the donation:");
						String type = sc.nextLine();

						System.out.println("Please enter the name of raising company for the donation:");
						String name_of_fund = sc.nextLine();

						// choose the payment method for this donation
						System.out.println("Please choose the payment method(0 for check, 1 for credit card):");
						String payment_way = sc.nextLine();

						// if choose check
						if (payment_way.equals("0")) {
							System.out.println("Please enter the check number:");
							String check_number = sc.nextLine();

							String sqlInsert3;
							sqlInsert3 = "insert into person_donation_check values('" + SSN + "','" + date + "','"
									+ amount + "','" + type + "','" + name_of_fund + "','" + check_number + "')";
							try {

								stmt.executeUpdate(sqlInsert3);
							} catch (SQLException e) {
								System.err.println("SQLException: " + e.getMessage());
							}
						}
						// if choose credit
						else {// if it is 1 insert the information of the credit card

							System.out.println("Please enter the card number:");
							String card_number = sc.nextLine();

							System.out.println("Please enter the card type:");
							String card_type = sc.nextLine();

							System.out.println("Please enter the expiration date:");
							String expiration_date = sc.nextLine();

							String sqlInsert3;
							sqlInsert3 = "insert into person_donation_card values('" + SSN + "','" + date + "','"
									+ amount + "','" + type + "','" + name_of_fund + "','" + card_number + "','"
									+ card_type + "','" + expiration_date + "')";
							try {
								stmt.executeUpdate(sqlInsert3);
							} catch (SQLException e) {
								System.err.println("SQLException: " + e.getMessage());
							}

						}
						System.out.println("Please enter the next date of the donation or enter Q to quit:");
						date = sc.nextLine();
					}
				}

				// 9. Enter a new organization and associate it with several donations.
				if (option == 9) {
					String date = null;
					System.out.println("Please enter the name of the organization:");
					String organization_name = sc.nextLine();

					System.out.println("Please enter the mailling address:");
					String mailling_address = sc.nextLine();

					System.out.println("Please enter the phone number of the organization:");
					String phone_number = sc.nextLine();

					System.out.println("Please enter the name of contact person of the organization:");
					String contact_person = sc.nextLine();

					System.out.println("Please enter the if the organization is anonymous(0 for no, 1 for yes):");
					String is_anonymous = sc.nextLine();

					String sqlInsert2 = "insert into organization values('" + organization_name + "','"
							+ mailling_address + "','" + phone_number + "','" + contact_person + "','" + is_anonymous
							+ "')";

					try {
						stmt.executeUpdate(sqlInsert2);
					} catch (SQLException e) {
						System.err.println("SQLException: " + e.getMessage());
					}

					System.out.println("Please enter the date of the donation, enter Q to quit:");
					date = sc.nextLine();
					while (!date.equals("Q")) {

						System.out.println("Please enter the amount of the donation:");
						String amount = sc.nextLine();

						System.out.println("Please enter the type of the donation:");
						String type = sc.nextLine();

						System.out.println("Please enter the name of fund of the donation:");
						String name_of_fund = sc.nextLine();

						// choose the payment method for this donation
						System.out.println(
								"Please choose the payment method(enter 0 for check,enter 1 for credit card):");
						String payment_way = sc.nextLine();

						// if it is check
						if (payment_way.equals("0")) {
							System.out.println("Please enter the check number:");
							String check_number = sc.nextLine();

							String sqlInsert3;
							sqlInsert3 = "insert into organization_donation_check values('" + organization_name + "','"
									+ date + "','" + amount + "','" + type + "','" + name_of_fund + "','" + check_number
									+ "')";
							try {
								stmt.executeUpdate(sqlInsert3);
							} catch (SQLException e) {
								System.err.println("SQLException: " + e.getMessage());
							}
						}
						// if it is credit
						else {

							System.out.println("Please enter the card number:");
							String card_number = sc.nextLine();

							System.out.println("Please enter the card type:");
							String card_type = sc.nextLine();

							System.out.println("Please enter the expiration date:");
							String expiration_date = sc.nextLine();

							String sqlInsert3;
							sqlInsert3 = "insert into organization_donation_card values('" + organization_name + "','"
									+ date + "','" + amount + "','" + type + "','" + name_of_fund + "','" + card_number
									+ "','" + card_type + "','" + expiration_date + "')";
							try {
								stmt.executeUpdate(sqlInsert3);
							} catch (SQLException e) {
								System.err.println("SQLException: " + e.getMessage());
							}

						}
						System.out.println("Please enter the next date of the donation,enter Q to quit:");
						date = sc.nextLine();
					}
				}

				// 10. Retrieve the name and phone number of the doctor of a particular client.
				if (option == 10) {
					System.out.println("Please enter the SSN of the client:");
					String SSN = sc.nextLine();

					ResultSet rset = stmt.executeQuery(
							"select doctor_name, doctor_phone_number from client where SSN = '" + SSN + "'");
					System.out.println("Doctor_name      Doctor_phone_number");
					while (rset.next()) {
						System.out.println(rset.getString(1) + "               " + rset.getString(2));
					}
				}

				if (option == 11) {
					try {
						System.out.println("Please enter the date of expenses:");
						String date1 = sc.nextLine();
						System.out.println("Please enter the next date of expenses:");
						String date2 = sc.nextLine();

						String inst = "select SSN, sum(amount) from expense where \"date\" between '" + date1
								+ "' and '" + date2 + "' group by SSN order by sum(amount)";
						ResultSet rset = stmt.executeQuery(inst);
						System.out.println("Name   Sum_of_Amount");
						while (rset.next()) {
							System.out.println(rset.getString(1) + "       " + rset.getString(2));
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}

				// 12. Retrieve the list of volunteers that are members of teams that support a
				// particular client.
				if (option == 12) {
					try {
						System.out.println("Please enter the SSN of client:");
						String SSN = sc.nextLine();

						String inst = "select SSN, name from person where SSN in (select SSN from person where SSN in (select SSN from serve where team_name in(select team_name from care where SSN = '"
								+ SSN + "')))";
						ResultSet rset = stmt.executeQuery(inst);

						System.out.println("Volunteer_SSN    Volunteer_Name");
						while (rset.next()) {
							System.out.println(rset.getString(1) + "                " + rset.getString(2));
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}

				// 13. Retrieve the names and contact information of the clients that are
				// supported by teams sponsored by an organization whose name starts with a
				// letter between B and K. The client list should be sorted by name.
				if (option == 13) {
					try {
						String inst = "select Name, Mailing_Address, Email_Address, Home, Work, Cell_Phone from Person Where SSN in (Select SSN from care Where Team_name In(Select team_name From Sponsor Where Organization_Name between 'B' and 'K~'))";
						ResultSet rset = stmt.executeQuery(inst);
						System.out.println(
								"Client_Name   Mailing_Address   Email_Address   Home   Work_Phone   Cell_Phone");

						while (rset.next()) {
							System.out.println(rset.getString(1) + "          " + rset.getString(2) + "           "
									+ rset.getString(3) + "        " + rset.getString(4) + "         "
									+ rset.getString(5) + "        " + rset.getString(6));
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}

				// 14. Retrieve the name and total amount donated by donors that are also
				// employees. The list should be sorted by the total amount of the donations,
				// and indicate if each donor wishes to remain anonymous.
				if (option == 14) {
					try {
						String inst = "select name, sum_amount from person join (select SSN, sum(amount) as sum_amount from (select SSN, amount from person_donation_card where SSN in(select SSN from employee) union all select SSN, amount from person_donation_check where SSN in(select SSN from employee)) group by SSN) using(SSN)";
						ResultSet rset = stmt.executeQuery(inst);

						System.out.println("Donor_Name   Total_Amount");
						while (rset.next()) {
							System.out.println(rset.getString(1) + "         " + rset.getString(2));
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}

				// 15. For each team, retrieve the name and associated contact information of
				// the volunteer that has worked the most total hours between March and June.
				if (option == 15) {
					try {
						String inst = "select name, mailing_address,email_address,home,person.work,cell_phone from person where SSN in(select ssn1 from (select ssn1, hour1,team_name1 from (select SSN as ssn1,sum(hour)as hour1,team_name as team_name1 from serve_month_hour where month between 3 and 6 group by SSN,team_name), (select team_name as team_name2,max(T)as hour2 from (select SSN,sum(hour)as T,team_name from serve_month_hour where month between 3 and 6 group by SSN,team_name) group by team_name) where team_name1=team_name2 and hour1=hour2)group by ssn1)";
						ResultSet rset = stmt.executeQuery(inst);
						System.out.println(
								"Volunteer_Name   Mailing Address   Email_Address   Home_Phone   Work_Phone   Cell_Phone");

						while (rset.next()) {
							System.out.println(rset.getString(1) + "              " + rset.getString(2)
									+ "             " + rset.getString(3) + "         " + rset.getString(4) + "       "
									+ rset.getString(5) + "       " + rset.getString(6));
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}

				// 16. Increase the salary by 10% of all employees to whom more than one team
				// must report.
				if (option == 16) {
					try {
						String inst = "update employee set salary = salary*1.1 where SSN in (select SSN from report group by SSN having count(*) > 1)";
						ResultSet rset = stmt.executeQuery(inst);
						while (rset.next()) {
							System.out.println(rset.getString(1));
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}

				// 17. Delete all clients who do not have health insurance and whose value of
				// importance for transportation is less than 5.
				if (option == 17) {
					try {
						String inst = "delete from client where SSN in(select SSN from has_insurance minus (select distinct SSN from has_insurance, insurance_policy where has_insurance.policy_id = insurance_policy.policy_id and type = 'health')\r\n"
								+ "INTERSECT select distinct SSN from need where type = 'transportation' and importance < 5);";
						ResultSet rset = stmt.executeQuery(inst);
						System.out.println("Employee_Name   Salary");

						while (rset.next()) {
							System.out.println(rset.getString(1) + "  " + rset.getString(2));
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}

				// 18. Import: Enter new teams from a data file until the file is empty (the
				// user should be asked to enter the input file name).
				if (option == 18) {
					// read file name
					System.out.println("Please enter file name or full file path: ");
					String fileName = sc.nextLine();
					File file = new File(fileName);
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new FileReader(file));
						String oneline = reader.readLine();
						while (oneline != null) {
							// split according white space to get each column
							String[] oneteam = oneline.trim().split("\\s+");
							try {
								String inst = "insert into team values('" + oneteam[0] + "','" + oneteam[1] + "','"
										+ oneteam[2] + "')";
								stmt.executeUpdate(inst);
							} catch (SQLException sa) {
								System.out.println(sa.getMessage());
							}
							oneline = reader.readLine();
						}
					} catch (Exception e) {
					}
				}

				// 19. Export: Retrieve names and mailing addresses of all people on the mailing
				// list and output them to a data file instead of screen (the user should be
				// asked to enter the output file name).

				if (option == 19) {
					ResultSet rest = stmt
							.executeQuery("select name, mailing_address from person where is_on_mailing_list = '1'");
					System.out.println("Please enter file name or full file path:");
					String fileName = sc.nextLine();
					BufferedWriter bw = null;
					FileWriter fw = null;
					try {
						fw = new FileWriter(fileName);
						bw = new BufferedWriter(fw);
						while (rest.next()) {
							// concatenate to a string
							bw.write(rest.getString(1) + " " + rest.getString(2));
							bw.newLine();
						}
						bw.close();
					} catch (Exception e) {
						e.getMessage();
					}
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}