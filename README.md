# Getting Help
###### Carlos Barcelos | 24 April 2017

# Run:
  - Open the executable JAR titled "GettingHelp_Carlos Barcelos"
  - Make sure that the XML file is in the same level directory as the executible JAR and be titled "manpages.xml"

# Operation:
  - Type the command name into the search bar and press "Search" or press the "Enter" key in order to retrieve the man page for that command
    -- Available commands: "ls", "cs", "mkdir", "rmdir", "alias", "ar", "basename", "batch"
  - Searching for an invalid command will result in a "404" command not found result
  - In order to get help information search for "HELP" or click the "Help" button at the bottom left of the window

# Notes:
  - The program reads the XML file at the start of execution, changing the contents of the file while the application
 is open will not dynamically change the contents
 
 <hr>
 
 # Formal Specifications
 The entire program operates through a single screen. Every action can be made from the one screen and the only information that changes is the right-hand, information pane. The information in each of the three rows updates dynamically to represent the state of the system. The following is the main screen of the interface and the specifications of the system:
The final prototype created is in line with the project specifications. The task was to create an XML reader for Man pages and have those results output to the screen in a structured and layered way. This is completed by specifying which Man page to access (1), selecting to search for the page (2) and then viewing the page in three layered boxes: command topic (3), command summary (4) and command details (5).

The design required the use of menus and buttons. There is a tree structured menu (6) that houses all the possible commands that are accessible through the system. There is a search button (2) and a help button (7) to give power to the user.

Finally, it was specified that the interface be kept simple. I believe that this interface achieves that. The interface in compact with very few parts to it. There are two buttons in total and very few actions that can be taken. This is not to say that the interface is not feature rich, an example of this is the double click feature. Keeping advanced features invisible allows new users to easily grasp the initial interface while giving advanced users tools to assist them.

![Man Page Interface](/src/images/manpage.jpg?raw=true "Figure 1 The man page interface with numbered parts")

The following state diagram states at the Welcome screen and move to any of the other screens from there. The oval states represent the possible sates of the system. The rectangles represent the method for getting to each of the states. The arrows going between the states represent paths to get between each state. Note that once the Welcome Screen is left there is no way to get back to it. Also, each of the three main states – the Man Page, the Page Not Found and the Help Page – have paths between each other such that each one state can get to the other two.

![State Transition Diagram](/src/images/statediagram.jpg?raw=true "Figure 2 State transition diagram")

 ## Content Diagram
 The content diagram below represents a logical view of the system as a while. The two directional arrows imply that the two items can access each other and flow through each other. For example, while on a Man Page it is possible to Enter Search Details to get to a Page Not Found, a Help Page or another Man Page.
 
![Content Diagram](/src/images/contentdiagram.jpg?raw=true "Figure 3 Content diagram")
 
 # Testing
 ## Initial Testing
 Working out the design on paper (see attached Project 4: Rough Design) I realized that there was no Help Page. I planned for there to be usage information when the application was first opened but I realized that once this information went away it could not be called back. As a result, I added a help button to the final design. The help button does not display the same information as the Welcome Page but it is similar enough to get the user going in the right direction on how to use the application.
 
 ![Help Button](/src/images/helpbutton.jpg?raw=true)

While paper testing, I realized that all the elements had the same background color and were not well spaced. This led me to put more white space that originally intended between the three information panes and use a different background color for the search pane. Using colors and whitespace (as seen in the design above) created a more visually distinctive design than the original paper prototype.

 ## Prototype Testing
 Most my testing and changes happened during the prototype stage. This was because with the prototype I could observe how the design behaved and experience any technical constraints that it came with. The first thing I tested was the searching feature. I realized that typing in the text box and then moving the mouse to the “Search” button every time I wanted to search a command was tedious. Because of how frequently this task would be required I decided to shorten it in two ways:
 
 1) **Tree nodes are selectable**
The list of tree nodes was made selectable to not only show the user what commands are in the system but also allow them to quickly access those commands. Selecting the command from the tree fills the search text box with the command name. From there the user my click the search button to get the man page information on the command.

 2)	**Pressing the “Enter” key activates the search**
Pressing “Enter” to search was an important feature to add because it is natural to any user familiar with computers. So often, “Enter” is used to select an on-screen action or activate a button. Having that feature in this interface not only speed up the searching process but made the interface feel more natural during testing.

I then took this design one step further and implemented a “double click to search” feature. Double clicking on a command in the tree view will automatically search for that item and display the results in the information pane. This is a robust feature; it is useful for the experienced user for ease-of-access by performing searches with just the mouse; it is error tolerant in that an automatic search only occurs when a double click is registered; it behaves similarly to another double click features the user is familiar with. Overall, the double click feature combines the objectives of the previous two testing results.

Working with the prototype design I was also able to observe how the interface window felt relative to my computer monitor. For example, I could alter and observe the initial window size and position. Because I could try different window designs, I decided to have the window open in the middle of the screen and be large enough to see but not so large that it took up the entire screen. This design decision was a result of using the interface while still trying to gather information from the rest of the screen; the testing environment I just described is similar to the environment in which the user would be using this program, having it one screen while not obstructing the entire screen. However, the exact sizing and positioning is entirely subject and because of this it entirely customizable through the interface.

![Program in use](/src/images/inuse.jpg?raw=true "Figure 4 Viewing the interface as an unobtrusive addition to the user's main screen")

 # Design Rationale
 ## The user and their task
 The layout of the interface was decided based on the probable handedness of the user. As mentioned during the user analysis, the percentage of right-handed people is 88% therefore the design was built with right-handed users in mind. The result was that selectable information was placed on the left of the interface where the user would have more dexterity while using a mouse.
The user was considered to have been using a computer for an extended period if they are refereeing to manual pages. Due to this analysis, I decided to make the interface darker than the traditional white background. I chose a dark gray background with white text to contrast to put less of a strain on the user’s eyes. The previously described “Enter” and “double click” features were in consideration of the user who is sitting at the interface for a long time. Because of their extended use session, it is important that they have these sorts of additional features to speed up frequently used tasks.

The search bar and button were placed at the top of the interface for increased visibility. It was important that the user can easily see the search features because it is the most important tool they have for finding the man page of a specific command.
The command menu was the next most important feature to have featured. The search tree is titled “Commands” and organized alphabetically. Alphabetical sort is an easy to understand, natural search method. Before any alphabetical sorting there is a “Frequently Used” folder for commands that are searched for often. In this interface, the commands listed in the Frequently Used section are placed their statically but in a finalized interface they would be dynamic. Frequently Used commands should be initialized based on some global statistic and then refined locally for the user. For example, the more the user uses the interface the more personalized the Frequently Used sections becomes.

Finally, the “Help” button is at the bottom of the selection pane so that it is out of the way. It is not expected that the user will have to use the Help button as frequently as the other buttons and features so it is placed out of the way. It takes up the entire width of the selection pane so that, while it is out of the way, it remains accessible.

 ## Analysis of interaction
The system has a smooth feeling to it. When the program first opens, the user is greeted by a Welcome Page. This screen describes what each piece of the information pane lists. This information is non-recoverable once the user selects any sort but the information can still be viewed using mouse over text.

![Welcome Screen](/src/images/welcomescreen.jpg?raw=true "Figure 5 Welcome screen when program is initially launched")

The user will then select a command to find a man page for. This can be done by the double click method or manual text entry. When a command is successfully searched for the commands information is displayed in the information pane in each of the layered parts.

![Successful Search](/src/images/successfulsearch.jpg?raw=true "Figure 6 Result of a successful command search")

To get a clearer view of all the commands available, the user may expand the command tree. The tree is fully expandable and collapsible like other tree interfaces of a similar nature. Each branch indicates whether it has been expanded by changing from an un-expanded to an expanded icon.

![Tree View](/src/images/treeview.jpg?raw=true "Figure 7 Expanded tree view of commands. "..." implies that there are more commands but they have not been yet implemented")

If the user searches for a command which does not exist or misspells a command they will be greeted with the “404’ page. 404 was used to specify that the command was not found because of the user’s familiarity with webpages. The page instructs the user to “Please try again”. When the 404 page first appears, the system creates an audible tone to alert the user that something has gone wrong. The tone used is the default alert tone to ensure that the user is not confused as to what made the noise or what the noise means.

![Command Not Found](/src/images/commandNotFound.jpg?raw=true "Figure 8 Command not found page")

Finally, if the user needs more help they can select the “Help” button or search for “HELP” in the search bar. This will bring up the help page explaining what each section of the information pane relates to and how to make a successful search.

![Help Screen](/src/images/helpscreen.jpg?raw=true "Figure 9 Help screen. Obtained by searching for "HELP" or clicking the "Help" button")
