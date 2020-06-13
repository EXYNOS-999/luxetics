# LUXETICS  
## TOOLS|FRAMEWORKS    



**PsyToolkit**: A software package for programming psychological experiments using Linux

[PsyToolkit: A software package for programming psychological experiments](https://link.springer.com/content/pdf/10.3758/BRM.42.4.1096.pdf)

*Summary*  
**Software includes:**  


1. A high-level scripting language, a library for the programming language C, and a questionnaire presenter.  
2. Integraion with statistical software package R. PsyToolkit
3. Support for external hardware (including IoLab and Cedrus response keyboards and two common digital input/output boards) and to support millisecond timing precision, which is essential for Cogni.



***Hypothesis***  
The flanker task, also known as the Eriksen flanker task, was designed in the early 1970s by Eriksen and Eriksen. 
  
*The basic effect is that when you need to respond to stimuli "flanked" by irrelevant stimuli, the irrelevant stimuli can still affect your response.*






## METHOD | IMPLEMENTATION 

The flanker task proposed is slightly different from the original flanker task from Eriksen and Eriksen (1974) better suited for Luxetic's purpose.


**Rules**
In this task, you need to respond with the A or L key of your keyboard. This matches the original task’s left or right lever press (A and L are left and right on the keyboard, respectively).











## DEMO 

[Eriksen flanker task](https://github.com/EXYNOS-999/luxetics/blob/master/experiment_flanker.html)

*Details* 

5 letters appear above the fixation point, but you must only respond to the central letter (which is above the fixation point). The letters X and C need to be responded to with the A button of your keyboard. The letters V and B need to be responded to with the L button of your keyboard. If there is a mismatch between the flanking letters their response and the response required by the central letter, we speak of a "incongruent" or "incompatible" condition.





*Data output file*

The save line of the PsyToolkit experiment script determines what is being saved in the data output file. Typically, for each experimental trial, you would have exactly one line in your text file, and each number/word on that line gives you the information you need for your data analysis, such as the condition, response speed, and whether an error was made.

Output format:

**Column**        |   **Meaning**     

1   			  |stimulus text    
2             | 1 = congruent ; 0 = incongruent     
3             | status (1=correct, 2=error, 3=too slow)     
4             |	the response time (ms)




## DATA ANALYSIS \

average score (e.g., average response time or error rate) the participant in each condition, and analyze those data further.

If the participant carries out the same conditions, "repeated measures" analysis on the dependent variable.

Alternatively, you can analyze the data of the participants in more detail before entering their data into the group analysis.


Personal Preference for data analysis: https://www.r-project.org 

Exa