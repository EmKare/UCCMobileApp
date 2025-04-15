package com.example.uccmobileapp

val ITCourses = listOf(
    Course("ITT101",
        "Computer and Information Systems",
        3,
        emptyList(),
        "This introductory course provides the necessary background for " +
                "understanding the role of information systems in organizations and " +
                "for using computer tools and technology in solving business problems. " +
                "The main concepts covered include types and categories of computers, " +
                "software and hardware components, storage, computer networks and " +
                "operating systems with an emphasis on analyzing problems and creating solutions. " +
                "In the practical section of the course students will get hands-on experience using " +
                "office productivity tools."
    ),
    Course("ITT103",
        "Programming Techniques",
        3,
        listOf("ITT101"),
        "This course will introduce students to programming concepts. " +
                "Students will learn proper programming design techniques and principles. " +
                "The focus is on developing the logic and thought-processes required for problem solving, " +
                "rather than on learning a programming language. " +
                "This course assumes no prior knowledge of programming, " +
                "however successful students will be those with an aptitude for problem solving. " +
                "Programming Techniques serves as the foundation course for all other programming " +
                "courses in the program."
    ),
    Course("ITT200",
        "Object-Oriented Programming using C++",
        3,
        listOf("ITT103"),
        "This course aims to broaden the student’s knowledge of concepts and features " +
                "of an object-oriented programming language. Students will be required to use " +
                "these concepts to design solutions for real world problems."
    ),
    Course("ITT201",
        "Data Communications and Networks I",
        3,
        listOf("ITT101"),
        "This course will introduce students to programming concepts. " +
                "Students will learn proper programming design techniques and principles. " +
                "The focus is on developing the logic and thought-processes required for problem solving, " +
                "rather than on learning a programming language. " +
                "This course assumes no prior knowledge of programming, " +
                "however successful students will be those with an aptitude for problem solving. " +
                "Programming Techniques serves as the foundation course for all other programming " +
                "courses in the program."
    ),
    Course("ITT203",
        "Data Structures & File Management I",
        3,
        listOf("ITT200"),
        "This course will introduce students to programming concepts. " +
                "Students will learn proper programming design techniques and principles. " +
                "The focus is on developing the logic and thought-processes required for problem solving, " +
                "rather than on learning a programming language. " +
                "This course assumes no prior knowledge of programming, " +
                "however successful students will be those with an aptitude for problem solving. " +
                "Programming Techniques serves as the foundation course for all other programming " +
                "courses in the program."
    ),
    Course("ITT208",
        "Internet Authoring I",
        3,
        listOf("ITT103"),
        "This introductory course in Internet Authoring will introduce students to " +
                "the tools needed to develop and publish Web Sites. At the end of this course " +
                "students should be able to comfortably design, develop and publish their " +
                "site on the Internet. Students will also be exposed to selected internet authoring " +
                "tools to develop and publish web pages."
    ),
    Course("ITT210",
        "Database Management Systems",
        3,
        listOf("ITT103"),
        "This introductory course covers the concepts related to the design and implementation " +
                "of Database Management Systems. Case studies will be used to give students a practical " +
                "sense of the issues to be considered in the implementation and use of Information technology, " +
                "and to introduce the concepts, theories and laws or legal frameworks within which these issues " +
                "are managed."
    ),
    Course("ITT209",
        "Building Applications Using C#",
        3,
        listOf("ITT200, ITT208, ITT210"),
        "This course exposes students to the development of desktop and internet applications " +
                "using the popular C# language. Accordingly, students will be exposed to basic OOP concepts " +
                "such as classes, inheritance and overloading. Additionally, basic programming concepts such as " +
                "variable creation and manipulation, modularity and the use of control structures will be reviewed. "
    ),
    Course("ITT302",
        "Operating Systems",
        3,
        emptyList(),
        "The course introduces the fundamentals of operating systems design and implementation. " +
                "Topics include an overview of the components of an operating system, mutual exclusion and " +
                "synchronization, implementation of processes, scheduling algorithms, memory management, and " +
                "file systems. The course explains the issues the influence the design of contemporary operating " +
                "systems and may include a laboratory component to enable students to experiment with operating " +
                "systems."
    ),
    Course("ITT303",
        "Programming Design using Java",
        3,
        listOf("ITT200"),
        "This introductory course in Java programming exposes the students to the fundamental concepts " +
                "of using Java to develop clearly written and well-structured object-oriented programs that address " +
                "real world problems. Students are expected to already have a knowledge of programming in general " +
                "as well as specific knowledge of programming in an object-oriented environment."
    ),
    Course("ITT310",
        "Systems Analysis & Design",
        3,
        listOf("ITT210"),
        "This course covers the design of information systems and takes students right through to " +
                "implementation and maintenance. The course will explore all aspects of the systems development " +
                "life cycle (SDLC). The classes will use case studies to give students a practical sense of " +
                "systems analysis and design and to introduce the concepts, methodologies, tools and techniques " +
                "that can be used to develop systems. "
    ),
    Course("ITT405",
        "Human Computer Interaction & Interface Design",
        3,
        listOf("ITT310, PSY100"),
        "This course examines the interaction between humans and computers and explores the design of " +
                "computer interfaces that are based on the abilities, limitations, and goals of the users. " +
                "Students will be introduced to the principles underlying the interaction of humans with " +
                "computers and the design of computer interfaces by reviewing the theories and current research " +
                "in the area."
    ),
    Course("ITT408",
        "Information Assurance and Security",
        3,
        listOf("ITT208, ITT210, ITT310"),
        "The information technology (IT) professional must understand, apply, and manage " +
                "information assurance and security (IAS) in computing, communication, and organizational " +
                "systems. It is also important for the IT professional to provide users with a framework to " +
                "be sufficiently security aware to be an asset to the organization rather than a liability. " +
                "This course includes operational issues, policies and procedures, attacks and defense mechanisms, " +
                "risk analyses, recovery, and information security."
    ),
    Course("ITT411",
        "Project+",
        3,
        emptyList(),
        "This course is aimed at providing the student with the tools and techniques needed for successful " +
                "Project management. The references will mainly be drawn from IT-related projects. This course will " +
                "certify that the successful candidate has important core knowledge of the project lifecycle, roles " +
                "and skills necessary to effectively initiate, plan, execute, monitor/control and close a project."
    ),
)