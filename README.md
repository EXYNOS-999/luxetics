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




## DATA ANALYSIS 

average score (e.g., average response time or error rate) the participant in each condition, and analyze those data further.

If the participant carries out the same conditions, "repeated measures" analysis on the dependent variable.

Alternatively, you can analyze the data of the participants in more detail before entering their data into the group analysis.


Personal Preference for data analysis: https://www.r-project.org 

[Example of data analysis](https://github.com/EXYNOS-999/luxetics/blob/master/rstudio-screenshot.png)











##  OTHER EXPMPLES OF COGSCI TESTS 
Inhibition Of Return
Inhibition Of Return is the phenomenon that people respond slower to a stimulus which occurs at the same position as a target. The phenomenon was first described by Michael Posner.

In the following experiment, we have the following sequence of events:

Two yellow frames, left and right of a fixation cross are simultaneously presented
One of the two frames flashes. This should attract the attention to either the left of the right. This is called the IOR Cue.
About a second later, within one of the two frames, a white dot appears (i.e., the IOR target), which requires an immediate left or right button press (left or right shift button)


	# File: examples/ior/ior2.psy
	
	# example script doing an inhibition of return experiment
	# everything following a hash mark is a comment and is ignored
	# by the script interpreter
	
	options
	 window     # remove this if you want to run full screen
	 escape     # you can escape the program
	 bitmapdir  bitmaps # directory where to find bitmaps
	 origin topleft
	
	bitmaps      # define some bitmap names
	 frame       # if bitmaps have png extension, you do not need to specify
	 plus        # the whole file name. 
	 dot         
	 instruction 
	
	table standard    # table with 5 columns, which can be address with @-sign
	#1                             #2 #3  #4   #5
	#--------------------------------------------
	 "cueleft  targetleft  cued"   2  200 200  1 
	 "cueleft  targetright uncued" 2  200 600  2 
	 "cueright targetleft  uncued" 3  600 200  1 
	 "cueright targetright cued"   3  600 600  2 
	
	task ior
	 table standard               # use this table for choosing conditions
	 keys lshift rshift           # use 2 shift keys in this experiment
	 draw off                     # hold off drawing                   
	 show bitmap plus             # 1
	 show bitmap frame 200 300    # 2
	 show bitmap frame 600 300    # 3
	 draw on                      # now draw all three items
	 delay 1500                   # wait 1500 ms
	 clear @2                     # IOR cue; frame OFF attract attention
	 delay 100                    # wait 100 ms
	 show bitmap frame @3 300     # 4 ; frame ON again, still attacts attention
	 delay 860                    # wait 860 ms
	 show bitmap dot @4 300       # 5 ; show IOR target
	 readkey @5 1500              # wait max 1500 ms until correct key is pressed
	 clear 5                      # remove target from screen
	 save BLOCKNAME @1 STATUS RT  # save trial information
	
	message instruction           # show instruction, and wait for space key press
	
	block training                # training block
	 tasklist                     
	  ior 2                       # choose the ior task 5 times
	 end
	 system R CMD BATCH create-feedback.r
	 bitmap_from_file feedback.png
	 wait_for_key
	
	block main 5                   # 5 blocks of 10 trials
	 tasklist                     
	  ior 2                       # choose the ior task 5 times
	 end
	 system R CMD BATCH create-feedback.r
	 bitmap_from_file feedback.png
	 wait_for_key
	


*Analyze the data of a single subject R Script*

	File: examples/ior/create-feedback.r
	
	x = read.table("/dev/shm/data")
	
	postscript(file="feedback.ps",width=600,height=480)
	
	mins = rep(0,6)
	maxs = rep(0,6)
	mns  = rep(0,6)
	error= rep(0,6)
	
	BLOCK   = as.character(x[,1])
	RT1     = x[,6]
	
	blocks  = unique(BLOCK)
	
	CORRECT = x[,5] == 1
	
	data    = numeric(0)
	for ( i in 1:length(blocks) )
	  {
	    mns[i]  = round(mean(RT1[ CORRECT & BLOCK==blocks[i] ]),digits=1)
	    mins[i] = round(min(RT1[ CORRECT & BLOCK==blocks[i] ]),digits=1)
	    maxs[i] = round(max(RT1[ CORRECT & BLOCK==blocks[i] ]),digits=1)
	    error[i]= round(
	                sum(CORRECT & BLOCK==blocks[i])/sum(BLOCK==blocks[i])*100,digits=1)
	  }
	
	par(bg="black",fg="yellow",col.main="yellow",col.axis="yellow",col.lab="yellow")
	plot(0,0,xlim=c(1,6),ylim=c(100,max(maxs)+100),axes=F,
	     ylab="Reaction time (milliseconds)",xlab="block")
	
	title(paste("Times of last block:\n Mean:",mns[length(blocks)],"Slowest:",
	            maxs[length(blocks)],"Fastest:",mins[length(blocks)],
	            "\nSuccessrate:",error[length(blocks)],"%"),col="yellow")
	points(mins[1:length(blocks)],pch=19,col="green")
	points(maxs[1:length(blocks)],pch=19,col="red")
	points(mns[1:length(blocks)],pch=19,col="yellow",type="b",lwd=3)
	
	for ( i in 1:length(blocks))
	{
	  color = "green"
	  if ( error[i] < 90 ){color="orange"}
	  if ( error[i] < 80 ){color="red"}
	  text( i , 100 , error[i],col=color)
	}
	
	labs=c("T",paste(1:5,sep=""))
	axis(1,at=1:6,labels=labs)
	axis(2,las=1)
	
	dev.off()
	system("convert -rotate 90 feedback.ps feedback.png")
	
	
	




![VIZ](https://github.com/EXYNOS-999/luxetics/blob/master/ior.png)








## SCRIPTS FOR MEASURING RESPONSE TIME



**Eriksen flanker task**

		table mytable
	  "red rectangle"   1 255 0   
	  "green rectangle" 2 0   255
	
	task simpletask
	  table mytable
	  keys b n
	  delay 100
	  show rectangle 0 0 100 100  @3 @4 0
	  readkey @2 2000
	  clear 1
	  save @1 STATUS RT 
	
	block firstblock
	  tasklist
	    simpletask 10
	  end
		 
		 
		 

## Other Experiments

	Deary-Liewald Task
	
	Stroop Task
	
	Stimulus-Response Compatibility, Simon Task
	
	Mental Rotation Task
	
	Visual search
	
	N-back task (2 back)
	
	Task-switching paradigm (alternating runs version)
	
	Task-switching paradigm (cued version)
	
	Multitasking
	
	Wisconsin Card Sorting Task
	
	Fitts’s Law
	
	Cueing, Posner Task
	
	Inhibition Of Return (IOR)
	
	Endogenous vs exogenous cueing
	
	Lexical Decision Task
	
	ABBA task (reversed compatibility)
	
	Eriksen Flanker Task
	
	Attentional blink paradigm
	
	Implicit Association Task (IAT)
	
	Go/No-go task
	
	Stop signal task
	
	Psychological Refractory Period paradigm
	
	Negative Priming
	
	Navon task
	
	Corsi block test (short term memory measure)
	
	Corsi backward block test (short term memory measure)
	
	Iowa Gambling Task
	
	Mackworth Clock Task (vigilance)
	
	Visual Approach/Avoidance by the Self Task (VAAST) with words
	
	Visual Approach/Avoidance by the Self Task (VAAST) with images
	




## Whats Next/ Possible?

* Program a cognitive psychological experiment  
* Set up an online questionnaire/survey  
* Embed experiments in questionnaires/surveys  
* Run online studies  



## HYPER-PERSONALIZATION UNIQUE VALUE

Enhancing Cognitive Abilities with PBM 

Your brain learns from feedback. Through live visual feedback of what your brain is doing, you are able to train it to function better - neurofeedback. 

Using sensors on the scalp, we can measure and monitor this activity. With brain analysis software, we can identify what specific activity is giving rise to your symptoms.  

Once we know the areas of concern, we can create a training plan to help draw your brain into a comfortable, efficient state. That brings us to neurofeedback. 

[Q-EEG](https://brainworksneurotherapy.com/qeeg-brain-mapping)

**WE WILL REQUIRE SENSORS TO INGEST INPUT AND TRAIN OUR ML MODEL TO ADAP TO THE SAME.**

During a neurofeedback session, we compare what your brain is actually doing to what you'd like it to be doing. When your brain is nearing a more comfortable state, you are rewarded with a positive response on a computer screen. Usually this ‘neuro-feedback’ is in the form of a video game, music, or movie. In LUXETICS CASE THIS WOULD BE IN THE FORM OF A CHANGE IN THE FREQUENCY OF LIGHT.


*Machine Learning Arcitecture*

**RECURRENT NEURAL NETWORKS** 


Recurrent Neural Networks
Humans don’t start their thinking from scratch every second. As you read this essay, you understand each word based on your understanding of previous words. You don’t throw everything away and start thinking from scratch again. Your thoughts have persistence.

Traditional neural networks can’t do this, and it seems like a major shortcoming. For example, imagine you want to classify what kind of event is happening at every point in a movie. It’s unclear how a traditional neural network could use its reasoning about previous events in the film to inform later ones.

Recurrent neural networks address this issue. They are networks with loops in them, allowing information to persist.


![RNN](https://github.com/EXYNOS-999/luxetics/blob/master/RNN-rolled.png)

In the above diagram, a chunk of neural network, A(representation of the users state), looks at some input xt and outputs a value ht. A loop allows information to be passed from one step of the network to the next.

These loops make recurrent neural networks seem kind of mysterious. However, if you think a bit more, it turns out that they aren’t all that different than a normal neural network. A recurrent neural network can be thought of as multiple copies of the same network, each passing a message to a successor. Consider what happens if we unroll the loop:

![RNN-UNRPLLED](https://github.com/EXYNOS-999/luxetics/blob/master/RNN-unrolled.png)


Thus we can use this architecture, stating with a baseline for a individual and taking as input the analysis/scores from the various tests, and then passing it through the network and updating the user's state in the network.

Accordingly, furthur analysis can be done as to what level of difficulty changes and improvement/decine(qualitative) has occured in time t.

More advanced architectured such as LSTM(Long-Short Term memory units) can also be used which can capture long term dependencies(changes) in cognitive function.


[LSTM](https://colah.github.io/posts/2015-08-Understanding-LSTMs/)
[RNN-memory update](https://distill.pub/2019/memorization-in-rnns/)


*Alternate approach*


[REINFORCEMENT-LEARNING](https://medium.com/@jonathan_hui/rl-introduction-to-deep-reinforcement-learning-35c25e04c199)


[Must Watch](https://www.youtube.com/watch?v=JgvyzIkgxF0)

Basic Architecture 

![RL](https://www.kdnuggets.com/images/reinforcement-learning-fig1-700.jpg)

Where,

AGENT== USER 
  
ENVIROONMENT== TEST    

ANALYSIS RESULTS == r+1/s+1    CHANGE IN STATE OF THE MODEL FOR PERSONALIZATION  
 
ACTION== CHANGE IN THE COGNITIVE TASKS AND FREQUENCY OF LIGHT 

![RL-II](https://dkasenberg.github.io/assets/img/irl_diagram.png)

## REAL-TIME Cognitive-Analysis |  Brain Training 

## Why?

1. To improve mental well-being, performance & overall health
2. See and analyse Real-time progress/status of the data(processed) form the sensors.
3. In-app rewards; redemption towards next subscription ( Discussed: Marco )
4. Brain-Training process and analysis across different time-frames.
5. Create a feedback Mechanism(Reinforcement learning) for the brain to learn from periodic release of dopamine.(See also: neurofeedback)



## Research on Cognitive Training Games

CHESS    
[LICHESS API]("https://lichess.org/api") --> Connect to the Users Account    
Advantages:

- Can supplement with the analysis provided by LiChess as well.   

- Leveraging the Wide-populaity of CHESS.  


- [ELO RATING SYSTEM](https://en.wikipedia.org/wiki/Elo_rating_system#:~:text=The%20Elo%20rating%20system%20is,a%20Hungarian-American%20physics%20professor.&text=Two%20players%20with%20equal%20ratings,an%20equal%20number%20of%20wins.) Scientifically proven and standardized use across platforms and a good metric to reward(positive/negative) to both of the users.

- Open-Source no copyright issues(direct integration with the Luxetics platform) thus provind a more circular and complete experience. 


https://journals.plos.org/plosone/article?id=10.1371/journal.pone.0134467

https://www.lumosity.com/hcp/research/completed 

Note: We want to build on pudlished and proven work not random games and training 

https://www.lumosity.com/en/science/

https://blogs.scientificamerican.com/talking-back/best-evidence-for-brain-training-falls-short/

https://well.blogs.nytimes.com/2014/03/10/do-brain-workouts-work-science-isnt-sure/?_php=true&_type=blogs&_r=0

https://pubmed.ncbi.nlm.nih.gov/22708717/

Negative studies: can be usde to market and we can actualy show some metric of progres using our service where other traditional apps fall short.

https://www.cam.ac.uk/research/news/brain-training-app-may-improve-memory-and-daily-functioning-in-schizophrenia

Q: Better to target patients with specific disorders w/ games and cognitive tasks that have shown to make an improvement(backed by scientific studies)

Note: Lifestyle changes and reinforcemnt and rewards to do real-world tasks are better to implement and have shown +ve results 

https://www.labroots.com/trending/neuroscience/17249/brain-training-games-work


Some COGNITIVE GAMES WE CAN CONSIDER:


Discuss do we wanna go this route?