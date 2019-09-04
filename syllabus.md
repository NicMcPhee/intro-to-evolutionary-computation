

# CSci 4553: Evolutionary Computation and Artificial Intelligence

#### Fall 2019, MWF, 2:15-3:20pm, Sci 2185

#### University of Minnesota, Morris

#### Nic McPhee, mcphee@morris.umn.edu

---

Welcome to *Evolutionary Computation and Artificial Intelligence* (CSci
4553). This is a 4 credit course which meets MWF, 2:15-3:20pm in Sci 2185.
The pre-requisite for this course is CSci 2101, for both data
structures and programming experience. This course will be a combination
of conceptual work, readings in the literature of the field, and
hands-on experience (including a non-trivial amount of programming,
perhaps in languages you’re not terribly familiar with). There will also
be some basic statistical analysis and writing. Be prepared.

## Course description

The catalog description:

> Introduction to Evolutionary Computation as an Artificial Intelligence
> tool for developing solutions to problems that are difficult to
> describe precisely or solve formally, as well as comparisons with
> other AI techniques. Includes discussions of theoretical background
> and tools, implementation issues, and applications.

*Artificial intelligence* (AI) can be broadly seen as the attempt to build
computer systems that exhibit behavior that might be construed as
“intelligent” by people, at least some of the time. This has become a
*very* hot area in the last decade, with the success of projects like
AlphaGo, the progress in technologies like self-driving cars, and the
power of text and image analysis tools being by major players like Google
and Facebook.

*Evolutionary computation* (EC) is a problem solving technique
(sometimes considered a type of AI or *machine learning* (ML), but not always)
that uses simulated
evolution as a means of discovering solutions to problems. The basic
idea in EC is to start with a randomly generated population of possible
solutions. Most of these are probably pretty miserable, since they were
randomly generated, but some will presumably solve the problem at hand
better than others. Those better (but still not very good) solutions
will be selected and used to generate (typically via some kind of
recombination and mutation) a new population of candidate solutions.
These probably also won’t be very good, but hopefully they’re a little
better. Again the better individuals will be used to generate a new
population, and the process continues until an acceptable solution is
discovered or the user gets bored or frustrated and quits.

The core of this course will be an overview of the important role of
*search* in AI, and a survey of some of the key ideas in
evolutionary computation such as genetic algorithms and genetic
programming. As time allows, we'll also spend time going over some of
the basics of (deep) neural
networks since that's being an extremely hot area in the past few years.

We’ll do practical work where we implement specific
problems and run appropriate algorithms to (try to) find solutions to them,
we'll discuss some of the complicated ethical and social issues raised by
the advances and potential in ML and AI, we’ll
read and discuss research literature in the field, and we’ll learn how
to design, run, and statistically analyze experiments. We’ll realize all
this by reviewing (in roughly this order):

-   Some basic AI search tools. These are pretty old school, but still
    useful in a variety of settings and worth knowing a bit about.
-   Ethical and social implications of this work
-   Genetic algorithms (one of the earliest EC approaches)
-   Genetic programming (another EC approach, and the one I use is most
    of my research)
-   Neural networks (currently very successful in areas like image analysis)
-   Various applications of these concepts (including applications in
    the ever-popular area of playing games)

A key concept that I think should eventually be elevated to inclusion in
the official description is experience with empirical computer science.
Computer systems are widely used to collect and/or generate data in a
whole host of fields, and it’s important as computing professionals to
have at least some introductory skills at the collection and analysis of
that kind of data.

Some additional EC/AI optional topics that it would be cool to cover as
time and circumstances allow include:

-   Estimation of distribution algorithms (and N-gram
    genetic programming)
-   The impact of geography and locality on evolutionary processes
-   The use of type systems and grammars in genetic programming
-   The No Free Lunch (NFL) theorem for computer search
-   Theoretical analysis of evolutionary algorithms
-   Artificial neural networks and deep learning
-   Support vector machines (SVMs) and how they’re used in things like
    recommender systems
-   Ant colony optimization algorithms
-   Particle swarm optimization algorithms
-   Artificial life
-   Evolutionary robotics
-   Emergent phenomena
-   Readings on the complex question of what consciousness and
    self-awareness are, and what it would mean to realize these
    remarkable phenomena in a computer system.
-   Things the class is interested in :-)

## Course goals and learning objectives

Goals

-   Understanding of the history and key concepts in AI
-   Exposure to some of the key ethical and social implications of AI and ML
-   Understanding of key concepts, strengths and weaknesses of EC. This
    includes at a minimum evolutionary strategies, genetic algorithms,
    and genetic programming, but will include a variety of other
    techniques chosen in conjunction with the class.
-   Ability to configure and run a basic EC system, and do basic
    analysis of the results
-   Ability to collect and statistically analyze empirical data
    comparing different EC approaches
-   Ability to present research design and results in both written and
    oral form
-   Ability to use R to do appropriate statistical analysis and data
    visualization
-   Ability to use LaTeX to write up research reports
-   Ability to use LaTeX and Beamer to generate simple slides to support
    presentations
-   Ability to read, understand, and summarize in both oral and written
    form the content of research papers in the field

## Texts and other materials

This course has 3 rather unusual texts, all of which we will use and so
you should consider “required”. All are available in print form *and*
freely available on-line in electronic form . I would particularly
recommend getting Sean Luke’s book in print form as I think it’s a quite
excellent reference and would be worth having on your shelf for some
time to come.

-   Essentials of Metaheuristics, *Second edition* by Sean Luke:
    -   It’s not vital that you have the second edition (not that much
        changed), but it would be better to get the second.
    -   Paper copy:
        <http://www.lulu.com/shop/sean-luke/essentials-of-metaheuristics-second-edition/paperback/product-21080150.html>
    -   Free PDF copy: <http://cs.gmu.edu/~sean/book/metaheuristics/>
-   Evolved to Win by Moshe Sipper
    -   <http://www.lulu.com/shop/moshe-sipper/evolved-to-win/paperback/product-18702740.html>
-   A Field Guide to Genetic Programming by Poli, Langdon, & McPhee
    -   <http://www.gp-field-guide.org.uk/>

We’ll also read or watch other pieces on-line at as appropriate.

## Course calendar

See the course Canvas site for the calendar of activities and assignments outline of the course.

---

## Office hours and contact info

### Nic's Fall, 2019, office hours

My office hours for Fall, 2015, are:

-   Monday, 3:30-5pm
-   Tuesday, 1-2pm
-   Friday, 9-10am

I\'ll typically start my office hours in my office (Sci 1315), but I may
well move to the Computer Science labs (Sci 2610, 2630, and 2650) if, for example, a group has a question about a
project they\'re working on.
If I leave the office during office hours I\'ll try to leave a note on
my door telling you where I am, but you should always feel free to check
the labs if you\'re looking for me.

Those times almost certainly won\'t work for everyone. You\'re always
welcome to just drop by (see my [complete
schedule](http://www.google.com/calendar/hosted/morris.umn.edu/embed?src=mcphee%40morris.umn.edu&ctz=America/Chicago&mode=WEEK)),
and I\'m happy to make appointments anytime I\'m free.

If these times work very badly for you (e.g., you have class conflicts
all of these hours) then please let me know. If it turns out that a lot
of folks have major conflicts then I\'ll want to reconsider my choices.

### Nic's contact info

Email (mcphee@…) is my preferred method of contact, but be warned that I get a
*lot* of e-mail and bits get dropped now and then. Phone (office: 320-589-6320)
or a text is often better if it\'s critical or you need immediate feedback.

My office is Sci 1315, downstairs at the east end of the Science office wing.

I use [the U\'s Google
calendar](http://www.google.com/calendar/hosted/morris.umn.edu/embed?src=mcphee%40morris.umn.edu&ctz=America/Chicago&mode=WEEK)
to manage my calendar. Google may be the Evil Empire of the next decade,
but they make a fine on-line calendar tool. If you\'re using Google
calendar, feel free to use that to invite me to events/appointments.

---

## Course work and grading

This course will be primarily organized around a set of projects which
will in turn generate the gradable products. Some of these will be code,
some of these will be writing assignments, and some will be
presentations. The key components will be:

-   Readings and discussion (in-class & on-line) (15%)
-   Designing, implementing, and running EC/AI experiments (45%)
-   Literature review and presentation/discussion (15%)
-   Final project (25%)

These are roughly weighted according to the amount of time spent on
them.

Most if not all of these projects will be group projects. Groups will typically
consist of 2-4 students, and may be randomly generated. I may choose to
solicit peer evaluations of group work.

The primary products and factors used in grading will be:

-   Project write-ups (with an emphasis on focus, organization, clarity
    and completeness)
-   Class presentations (with an emphasis on focus, organization,
    clarity and completeness)
-   Quality of your code and revision control log information
-   Participation (which can include in-class discussion and
    out-of-class tools like forums)

Grades will be entered on the [course Canvas
site](https://canvas.umn.edu) and can be
checked there throughout the semester. If you have any questions or
concerns, please bring them up right away when it\'s much easier to
address them.

**Late work** will receive a penalty of 10% for every day that it is late. This
can be overwritten in case of illness and other unavoidable delays, but you'll
need to contact me promptly to address such cases.

### Grading standards (definition of grades)

[According to University of Minnesota policy](https://policy.umn.edu/education/gradingtranscripts):

-   A: Represents achievement that significantly exceeds expectations
    in the course.
-   B: Represents achievement that is above the minimum expectations in
    the  course.
-   C: Represents achievement that meets the minimum expectations in the course.
-   D: Represents achievement that partially meets the minimum expectations
    in the course. Credit is earned but it may not fulfill major or program
    requirements.

-   S: Satisfactory (equivalent to a C- or better)

-   F: Represents failure in the course and no credit is earned.

By default I will use the University Minnesota Letter Grade Scheme:

*   A: 100% to 93%
*   A-: <93% to 90%
*   B+: <90% to 87%
*   B: <87% to 83%
*   B-: <83% to 80%
*   C+: <80% to 77%
*   C: <77% to 73%
*   C-: <73% to 70%
*   D+: <70% to 67%
*   D: <67% to 60%
*   F: <60% to 0%

I will never be *less* generous than this, but I may be *more* generous
depending on the clustering and gaps in the grades. If, for example,
there's a dense cluster
from 88.5% to 86% followed by a gap, I *might* extend the B+ range down to 86%.
I make no promises that I will make such shifts, but it could happen.

### Senate academic workload policy

For undergraduate courses, one credit is defined as equivalent to an
average of three hours of learning effort per week (over a full
semester) necessary for an average student to achieve an average grade
(a C) in the course. For example, an average student taking a four
credit course that meets for four hours a week should expect to spend an
additional eight hours a week on course work outside the classroom in
order to get a C in that course.




------------------------------------------------------------------------

## Relevant policies and support services

Below are some of the most relevant policies and services.
There are many other potentially relevant university policies
and services, so definitely ask if you have questions or concerns.

### Accessibility and institutional support

The University of Minnesota views disability as an important aspect of diversity, and is committed to providing equitable access to learning opportunities for all students. The Disability Resource Center (DRC) is the campus office that collaborates with students who have disabilities to provide and/or arrange reasonable accommodations.

   * If you have, or think you have, a disability in any area such as, mental health, attention, learning, chronic health, sensory, or physical, please contact the DRC office on your campus (UM Morris 320.589.6178) to arrange a confidential discussion regarding equitable access and reasonable accommodations.
   * Students with short-term disabilities, such as a broken arm, should be able to work with instructors to remove classroom barriers. In situations where additional assistance is needed, students should contact the DRC as noted above.
   * If you are registered with the DRC and have a disability accommodation letter dated for this semester or this year, please contact your instructor early in the semester to review how the accommodations will be applied in the course.
   * If you are registered with the DRC and have questions or concerns about your accommodations please contact the Coordinator of the Disability Resource Center.

Additional information and contact information is available on the DRC website: <https://academics.morris.umn.edu/office-academic-success/disability-resource-center>, or you can go visit then in the Library.

### Harassment

University policy prohibits sexual harassment as defined by the University of Minnesota Regents' policy. In general, harassment or intimidation of others in the class for whatever reason is unacceptable (and hardly conducive to a successful learning environment).

### Equity, diversity, equal opportunity, and affirmative action

The University provides equal access to and opportunity in its programs and facilities, without regard to race, color, creed, religion, national origin, gender, age, marital status, disability, public assistance status, veteran status, sexual orientation, gender identity, or gender expression. For more information, please consult Board of Regents Policy.

### Academic Integrity

Academic integrity is essential to a positive teaching and learning environment. All students enrolled in University courses are expected to complete coursework responsibilities with fairness and honesty. Failure to do so by seeking unfair advantage over others or misrepresenting someone else's work as your own, can result in disciplinary action. The University Student Conduct Code defines scholastic dishonesty as follows:

> Scholastic dishonesty means plagiarism; cheating on assignments or examinations; engaging in unauthorized collaboration on academic work; taking, acquiring, or using test materials without faculty permission; submitting false or incomplete records of academic achievement; acting alone or in cooperation with another to falsify records or to obtain dishonestly grades, honors, awards, or professional endorsement; altering, forging, misrepresenting, or misusing a University academic record; or fabricating or falsifying data, research procedures, or data analysis.

Within this course, a student responsible for scholastic dishonesty can be assigned a penalty up to and including an "F" or "N" for the course. If you have any questions regarding the expectations for a specific assignment or exam, ask.
