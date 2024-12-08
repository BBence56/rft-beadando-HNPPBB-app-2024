const apiUrl = "http://localhost:8080"; // Cseréld ki a saját API-d címére

// Hallgatók lekérése
function fetchStudents() {
    fetch(`${apiUrl}/students`)
        .then(response => response.json())
        .then(data => {
            const studentList = document.getElementById('studentList');
            studentList.innerHTML = '';
            data.forEach(student => {
                const li = document.createElement('li');
                li.textContent = `ID: ${student.id}, Név: ${student.name}`;
                studentList.appendChild(li);
            });
        })
        .catch(error => console.error('Hiba a hallgatók lekérésekor:', error));
}

// Hallgató hozzáadása
function addStudent() {
    const id = document.getElementById('studentIdAdd').value;
    const name = document.getElementById('studentNameAdd').value;

    if (!name) {
        alert('A név megadása kötelező!');
        return;
    }

    fetch(`${apiUrl}/students`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id, name })
    })
        .then(() => alert('Hallgató hozzáadva!'))
        .catch(error => console.error('Hiba a hallgató hozzáadásakor:', error));
}



// Tanárok lekérése
function fetchTeachers() {
    fetch(`${apiUrl}/teachers`)
        .then(response => response.json())
        .then(data => {
            const teacherList = document.getElementById('teacherList');
            teacherList.innerHTML = '';
            data.forEach(teacher => {
                const li = document.createElement('li');
                li.textContent = `ID: ${teacher.id}, Név: ${teacher.name}`;
                teacherList.appendChild(li);
            });
        })
        .catch(error => console.error('Hiba a tanárok lekérésekor:', error));
}

// Tanár hozzáadása
function addTeacher() {
    const id = document.getElementById('teacherIdAdd').value;
    const name = document.getElementById('teacherNameAdd').value;

    fetch(`${apiUrl}/teachers`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id, name })
    })
        .then(() => alert('Tanár hozzáadva!'))
        .catch(error => console.error('Hiba a tanár hozzáadásakor:', error));
}


// Kurzusok lekérése
function fetchCourses() {
    fetch(`${apiUrl}/courses`)
        .then(response => response.json())
        .then(data => {
            const courseList = document.getElementById('courseList');
            courseList.innerHTML = '';
            data.forEach(course => {
                const li = document.createElement('li');
                li.textContent = `ID: ${course.id}, Név: ${course.name}`;
                courseList.appendChild(li);
            });
        })
        .catch(error => console.error('Hiba a kurzusok lekérésekor:', error));
}

// Kurzus hozzáadása
function addCourse() {
    const id = document.getElementById('courseIdAdd').value;
    const name = document.getElementById('courseNameAdd').value;
    const teacherId = document.getElementById('courseTeacherIdAdd').value;

    if (!name) {
        alert('A kurzus nevének megadása kötelező!');
        return;
    }

    if (!teacherId) {
        alert('A tanár ID megadása kötelező!');
        return;
    }

    fetch(`${apiUrl}/teachers/${teacherId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('A megadott tanár nem létezik!');
            }
            return fetch(`${apiUrl}/courses`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id, name, teacherId })
            });
        })
        .then(() => alert('Kurzus hozzáadva!'))
        .catch(error => console.error('Hiba a kurzus hozzáadásakor:', error));
}
//Beiratkozás hozzáadása
function addEnrollment() {
    const studentId = document.getElementById('enrollmentStudentId').value;
    const courseId = document.getElementById('enrollmentCourseId').value;

    if (!studentId || !courseId) {
        alert('A hallgató és a kurzus ID-k megadása kötelező!');
        return;
    }

    Promise.all([
        fetch(`${apiUrl}/students/${studentId}`),
        fetch(`${apiUrl}/courses/${courseId}`)
    ])
        .then(([studentRes, courseRes]) => {
            if (!studentRes.ok || !courseRes.ok) {
                throw new Error('A hallgató vagy kurzus nem létezik!');
            }
            return fetch(`${apiUrl}/enrollments`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ studentId, courseId })
            });
        })
        .then(() => alert('Beiratkozás sikeres!'))
        .catch(error => console.error('Hiba a beiratkozás során:', error));
}


// Beiratkozások lekérése
function fetchEnrollments() {
    fetch(`${apiUrl}/enrollments`)
        .then(response => response.json())
        .then(data => {
            const enrollmentList = document.getElementById('enrollmentList');
            enrollmentList.innerHTML = '';
            data.forEach(enrollment => {
                const li = document.createElement('li');
                li.textContent = `Hallgató ID: ${enrollment.studentId}, Kurzus ID: ${enrollment.courseId}`;
                enrollmentList.appendChild(li);
            });
        })
        .catch(error => console.error('Hiba a beiratkozások lekérésekor:', error));
}

// Beiratkozás törlése
function deleteEnrollment() {
    const studentId = document.getElementById('enrollmentStudentId').value;
    const courseId = document.getElementById('enrollmentCourseId').value;

    fetch(`${apiUrl}/enrollments?studentId=${studentId}&courseId=${courseId}`, {
        method: 'DELETE'
    })
        .then(() => alert('Beiratkozás törölve!'))
        .catch(error => console.error('Hiba a beiratkozás törlésekor:', error));
}
//Jegy hozzáadása
function updateGrade() {
    const studentId = document.getElementById('gradeStudentId').value;
    const courseId = document.getElementById('gradeCourseId').value;
    const gradeValue = document.getElementById('newGrade').value;

    if (!studentId || !courseId || !gradeValue) {
        alert('Minden mező kitöltése kötelező!');
        return;
    }

    Promise.all([
        fetch(`${apiUrl}/students/${studentId}`),
        fetch(`${apiUrl}/courses/${courseId}`)
    ])
        .then(([studentRes, courseRes]) => {
            if (!studentRes.ok || !courseRes.ok) {
                throw new Error('A hallgató vagy kurzus nem létezik!');
            }
            return fetch(`${apiUrl}/grades`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ studentId, courseId, grade: gradeValue })
            });
        })
        .then(() => alert('Osztályzat frissítve!'))
        .catch(error => console.error('Hiba az osztályzat frissítésekor:', error));
}

