const API = `http://localhost:${(new URLSearchParams(location.search)).get('apiPort')||'8080'}`;
const API_KEY = 'dev-demo-key-CHANGE_ME';


const $ = (sel) => document.querySelector(sel);
const loginSection = $('#login');
const appSection = $('#app');
const booksList = $('#books');
const loansList = $('#loans');
const userDiv = $('#user');
let auth = null; // { token, memberId, universityId, name }


async function api(path, opts={}) {
const res = await fetch(API+path, { headers: { 'Content-Type':'application/json', 'X-API-KEY': API_KEY }, ...opts });
if (!res.ok) throw new Error(await res.text());
return res.status===204 ? null : res.json();
}


$('#loginForm').addEventListener('submit', async (e) => {
e.preventDefault();
const fd = new FormData(e.target);
const payload = Object.fromEntries(fd.entries());
const data = await fetch(API+'/auth/login', { method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload) }).then(r=>r.json());
auth = { ...payload, token: data.token, memberId: data.memberId };
userDiv.textContent = `Signed in as ${auth.name} (${auth.universityId})`;
loginSection.classList.add('hidden');
appSection.classList.remove('hidden');
loadBooks();
loadLoans();
});


$('#searchBtn').addEventListener('click', loadBooks);


async function loadBooks() {
const q = $('#q').value.trim();
const list = await api('/books'+(q?`?q=${encodeURIComponent(q)}`:''));
booksList.innerHTML = '';
list.forEach(b => {
const li = document.createElement('li');
li.className = 'card';
li.innerHTML = `<strong>${b.title}</strong> <small class='badge'>${b.copiesAvailable}/${b.copiesTotal} available</small><br/><em>${b.author||''}</em><br/>
<div style='margin-top:8px;'>
<button ${b.copiesAvailable<=0?'disabled':''}>Borrow</button>
</div>`;
li.querySelector('button').onclick = async () => {
await api('/loans/borrow', { method:'POST', body: JSON.stringify({ universityId: auth.universityId, bookId: b.id }) });
await loadBooks();
await loadLoans();
};
booksList.appendChild(li);
});
}


async function loadLoans() {
const list = await api('/loans/member/'+auth.memberId);
loansList.innerHTML = '';
list.forEach(l => {
const due = new Date(l.dueOn).toISOString().slice(0,10);
const li = document.createElement('li');
li.className = 'card';
li.innerHTML = `<div><strong>Loan #${l.id}</strong> — Due: <span>${due}</span> — Renewals: ${l.renewals}</div>
}
