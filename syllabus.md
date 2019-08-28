

CSci 4553: Evolutionary Computation and Artificial Intelligence
==========================================================

Fall 2019, MWF, 2:15-3:20pm, Sci 2185
-------------------------------------------
University of Minnesota, Morris
-------------------------------

Nic McPhee, mcphee@morris.umn.edu
---------------------------------

Welcome to *Evolutionary Computation and Artificial Intelligence* (CSci
4553). This is a 4 credit course which meets MWF, 2:15-3:20pm in Sci 2185.
The pre-requisite for this course is CSci 2101, for both data
structures and programming experience. This course will be a combination
of conceptual work, readings in the literature of the field, and
hands-on experience (including a non-trivial amount of programming,
perhaps in languages you’re not terribly familiar with). There will also
be some basic statistical analysis and writing. Be prepared.

Course description
==================

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

Course goals and learning objectives
====================================

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

Texts and other materials
=========================

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

Course calendar
===============

See the course Canvas site for the calendar of activities and assignments outline of the course.

Course work and grading
=======================

This course will be primarily organized around a set of projects which
will in turn generate the gradable products. Some of these will be code,
some of these will be writing assignments, and some will be
presentations. The key components will be:

-   Readings and discussion (15%)
-   Small writing assignment (15%)
-   Designing, implementing, and running EC/AI experiments (45%)
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

Grades will be entered on the course Canvas site and can be checked
there throughout the semester. If you have any questions or concerns,
please bring them up right away when it’s much easier to address them.

------------------------------------------------------------------------

Stopping by for a chat, AKA "office hours"
==========================================

I am typically in my office, Sci 1315, or in the CSci labs (Sci 2610,
2630, and 2650) during the day, and you're always more than welcome to
come track me down, or e-mail me (mcphee@morris.umn.edu) or call me
(320-589-6320) to make an appointment.

Also, I use [the U's Google
Calendar](http://www.google.com/calendar/hosted/morris.umn.edu/embed?src=mcphee%40morris.umn.edu&ctz=America/Chicago&mode=WEEK)
to manage my calendar and it's *totally* fine to use it to see where I
might be, see when I'm free, or *just make an appointment*. If you add
an appointment to my calendar (presumably in a spot when I appear free),
I'll get an e-mail about it. If it looks like that time will work I'll
accept the appointment; otherwise I'll mail you back suggesting an
alternative.

My "official" office hours this semester are:

-   Monday, 3:30-4:30pm
-   Tuesday, 2-3pm
-   Friday, 9:30-10:30am

How to best get in touch and my contact info
============================================

The best way to contact me is probably e-mail (mcphee@morris.umn.edu),
although if you really need to get my attention and I'm looking swamped
(an all too common occurrence, I'm afraid) then your best bets are
probably:

-   Showing up at my office (Sci 1315, ).
-   Poke me via Google Hangouts (mcphee@morris.umn.edu); this is
    particularly nice if you're in the lab and want to find out if I'm
    around before you trek down to my office.
-   Call my office (320-589-6320).

My extended contact info can be found at <http://facultypages.morris.umn.edu/~mcphee/contact.html>

Relevant policies and support services
======================================

Grading standards (definition of grades)
----------------------------------------

-   A: Represents achievement that is outstanding relative to the level
    necessary to meet course requirements.
-   B: Represents achievement that is significantly above the level
    necessary to meet course requirements.
-   C: Represents achievement that meets the course requirements in
    every respect.
-   D: Represents achievement that is worthy of credit even though it
    fails to meet fully the course requirements.
-   S: Represents achievement that is satisfactory, which is equivalent
    to a C- or better.
-   F (or N): Represents failure (or no credit) and signifies that the
    work was either (1) completed but at a level of achievement that is
    not worthy of credit or (2) was not completed and there was no
    agreement between the instructor and the student that the student
    would be awarded an I (see also I).
-   I (Incomplete): Assigned at the discretion of the instructor when,
    due to extraordinary circumstances, e.g., hospitalization, a student
    who is otherwise doing well in the course is prevented from
    completing the work of the course on time. Requires a written
    agreement between instructor and student.

Academic dishonesty
-------------------

Academic dishonesty in any portion of the academic work for a course
shall be grounds for awarding a grade of F or N for the entire course.

University Senate academic workload policy
------------------------------------------

For undergraduate courses, one credit is defined as equivalent to an
average of three hours of learning effort per week (over a full
semester) necessary for an average student to achieve an average grade
(a C) in the course. For example, an average student taking a four
credit course that meets for four hours a week should expect to spend an
additional eight hours a week on course work outside the classroom in
order to get a C in that course.

------------------------------------------------------------------------

Academic Integrity
------------------

Academic integrity is essential to a positive teaching and learning
environment. All students enrolled in University courses are expected to
complete coursework responsibilities with fairness and honesty. Failure
to do so by seeking unfair advantage over others or misrepresenting
someone else's work as your own, can result in disciplinary action. [The
University Student Conduct
Code](http://regents.umn.edu/sites/regents.umn.edu/files/policies/Student_Conduct_Code.pdf)
defines scholastic dishonesty as follows:

> Scholastic dishonesty means plagiarism; cheating on assignments or
> examinations; engaging in unauthorized collaboration on academic work;
> taking, acquiring, or using test materials without faculty permission;
> submitting false or incomplete records of academic achievement; acting
> alone or in cooperation with another to falsify records or to obtain
> dishonestly grades, honors, awards, or professional endorsement;
> altering, forging, misrepresenting, or misusing a University academic
> record; or fabricating or falsifying data, research procedures, or
> data analysis.

Within this course, a student responsible for scholastic dishonesty can
be assigned a penalty up to and including an "F" or "N" for the course.
If you have any questions regarding the expectations for a specific
assignment or exam, ask.

Final words
-----------

-   Policy is subject to change, but only with advanced warning given in
    class suitably far in advance.
-   Major changes are unexpected.
-   It is University policy to provide reasonable accommodations to
    students with disabilities. This publication/material is available
    in alternative formats to persons with disabilities upon request.
    Please contact the Disability Services office, 589-6163, Room 362
    Briggs Library to discuss accommodation needs.
-   University policy prohibits sexual harassment as defined by the
    University of Minnesota Regents' policy:
    <http://regents.umn.edu/sites/regents.umn.edu/files/policies/SexHarassment.pdf>
-   Additional relevant university-wide policies may be found online:
    <http://policy.umn.edu/Policies/Education/Education/SYLLABUSREQUIREMENTS_APPA.html>.
-   You should work together whenever possible, but the act of copying
    or other forms of cheating will not be tolerated. Academic
    dishonesty in any portion of the academic work for a course is
    grounds for awarding a grade of F or N for the entire course. Any
    act of plagiarism that is detected will result in a mark of zero on
    the entire test or assignment for both parties. Please come and talk
    to me if you have any questions about what constitutes
    academic dishonesty. UMM's Academic Integrity policy and procedures
    can be found at the following website: Academic Integrity
    <http://committees.morris.umn.edu/academic-integrity>
-   As a student you may experience a range of issues that can cause
    barriers to learning, such as strained relationships, increased
    anxiety, alcohol/drug problems, feeling down, difficulty
    concentrating, and/or lack of motivation. These mental health
    concerns or stressful events may lead to diminished academic
    performance or reduce your ability to participate in
    daily activities. If you have any special needs or requirements to
    help you succeed in the class, come and talk to me as soon as
    possible, or visit the appropriate University service yourself. You
    can learn more about the range of services available on campus by
    visiting any of these websites:
    -   [The Academic Assistance
        Center](http://www.morris.umn.edu/services/dsoaac/aac)
    -   [Student
        Counseling](http://www.morris.umn.edu/services/counseling)
    -   [Disability
        Services](http://www.morris.umn.edu/services/dsoaac/dso)
    -   [Equity, Diversity & Intercultural
        Programs](http://www.morris.umn.edu/services/msp)

References
----------

-   [Official University policy on grade
    definitions](http://policy.umn.edu/Policies/Education/Education/GRADINGTRANSCRIPTS.html)
-   [Student conduct
    code](http://regents.umn.edu/sites/default/files/policies/Student_Conduct_Code.pdf)
-   [Official policy on makeup work for legitimate
    absences](http://policy.umn.edu/Policies/Education/Education/MAKEUPWORK.html)
-   [Board of Regents policy on student
    responsiblity](http://policy.umn.edu/Policies/Education/Education/STUDENTRESP.html)
-   [Board of Regents policy on sexual
    harassment](http://regents.umn.edu/sites/default/files/policies/SexHarassment.pdf)
-   [Board of Regents policy on equity, diversity, equal employment
    opportunity, and affirmative
    action](http://regents.umn.edu/sites/default/files/policies/Equity_Diversity_EO_AA.pdf)
-   [Board of Regents policy on disability
    services](http://regents.umn.edu/sites/default/files/policies/DisabilityServices.pdf)
-   [Board of Regents policy on academic freedom and
    responsibility](http://regents.umn.edu/sites/default/files/policies/Academic_Freedom.pdf)
