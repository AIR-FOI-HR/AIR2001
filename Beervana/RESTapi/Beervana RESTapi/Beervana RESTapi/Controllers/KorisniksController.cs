using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Beervana_RESTapi.Models;
using Beervana_RESTapi.Data;

namespace Beervana_RESTapi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class KorisniksController : ControllerBase
    {

        public AppDb Db { get; }
        public KorisniksController(AppDb db)
        {

            Db = db;
        }

        // GET api/blog
        [HttpGet]
        public async Task<IActionResult> GetLatest()
        {
            await Db.Connection.OpenAsync();
            var query = new KorisniksContext(Db);
            var result = await query.LatestPostsAsync();
            return new OkObjectResult(result);
        }

        // GET api/blog/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetOne(int id)
        {
            await Db.Connection.OpenAsync();
            var query = new KorisniksContext(Db);
            var result = await query.FindOneAsync(id);
            if (result is null)
                return new NotFoundResult();
            return new OkObjectResult(result);
        }

        // POST api/blog
        [HttpPost]
        public async Task<IActionResult> Post([FromBody] Korisnik body)
        {
            await Db.Connection.OpenAsync();
            body.Db = Db;
            await body.InsertAsync();
            return new OkObjectResult(body);
        }

        // PUT api/blog/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutOne(int id, [FromBody] Korisnik body)
        {
            await Db.Connection.OpenAsync();
            var query = new KorisniksContext(Db);
            var result = await query.FindOneAsync(id);
            if (result is null)
                return new NotFoundResult();
            result.IdUloga = body.IdUloga;
            result.IdClanstvo = body.IdClanstvo;
            await result.UpdateAsync();
            return new OkObjectResult(result);
        }

        // DELETE api/blog/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteOne(int id)
        {
            await Db.Connection.OpenAsync();
            var query = new KorisniksContext(Db);
            var result = await query.FindOneAsync(id);
            if (result is null)
                return new NotFoundResult();
            await result.DeleteAsync();
            return new OkResult();
        }

        // DELETE api/blog
        [HttpDelete]
        public async Task<IActionResult> DeleteAll()
        {
            await Db.Connection.OpenAsync();
            var query = new KorisniksContext(Db);
            await query.DeleteAllAsync();
            return new OkResult();
        }

    }
}
