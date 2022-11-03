document.addEventListener("DOMContentLoaded", function (event) {
    const sessions = document.getElementsByClassName('open_pop_up');

    const closePopUp = document.getElementById('pop_up_close');

    const popUp = document.getElementById('pop_up');

    for (let session of sessions) {

        session.addEventListener('click', function (event) {
            event.preventDefault();
            popUp.classList.add('active');
        });


    }

    closePopUp.addEventListener('click', () => {
        popUp.classList.remove('active');
    });
});
