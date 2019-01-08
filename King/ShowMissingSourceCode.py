import requests, os

problem_list = requests.get('https://uhunt.onlinejudge.org/api/p').json()
problem_id_data_map = {x[0]:x for x in problem_list}
problem_num_data_map = {x[1]:x for x in problem_list}

subm_list = requests.get('https://uhunt.onlinejudge.org/api/subs-user/422649').json()['subs']
passed_problems_id = set([x[1] for x in subm_list if x[2]==90])
passed_problems_num = sorted([str(problem_id_data_map[x][1]).zfill(5) for x in passed_problems_id])

existing_problems = set([x.split(' ')[0] for x in os.listdir() if x.split(' ')[0].isdigit()])
missing_problems_id = [x for x in passed_problems_num if x not in existing_problems]
if len(missing_problems_id) == 0:
    print('No missing!')
else:
    print('\n'.join([x+" "+problem_num_data_map[int(x)][2] for x in missing_problems_id]))